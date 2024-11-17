package com.project.selfoj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.selfoj.common.ErrorCode;
import com.project.selfoj.constant.CommonConstant;
import com.project.selfoj.exception.BusinessException;
import com.project.selfoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.project.selfoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.project.selfoj.model.entity.Question;
import com.project.selfoj.model.entity.QuestionSubmit;
import com.project.selfoj.model.entity.User;
import com.project.selfoj.model.enums.QuestionSubmitLanguageEnum;
import com.project.selfoj.model.enums.QuestionSubmitStatusEnum;
import com.project.selfoj.model.vo.QuestionSubmitVo;
import com.project.selfoj.service.QuestionService;
import com.project.selfoj.service.QuestionSubmitService;
import com.project.selfoj.mapper.QuestionSubmitMapper;
import com.project.selfoj.service.UserService;
import com.project.selfoj.utils.SqlUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Laido
 * @description 针对表【question_submit(题目提交)】的数据库操作Service实现
 * @createDate 2024-11-15 22:14:35
 */
@Service
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit>
        implements QuestionSubmitService {

    @Resource
    private QuestionService questionService;

    @Resource
    private UserService userService;

    /**
     * 提交题目
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    @Override
    public long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser) {
        //  判断实体是否存在 根据类别获取实体
        //  获取枚举值
        String language = questionSubmitAddRequest.getLanguage();
        QuestionSubmitLanguageEnum languageEnum = QuestionSubmitLanguageEnum.getEnumByValue(language);
        if (languageEnum == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "编程语言错误");
        }
        Long questionId = questionSubmitAddRequest.getQuestionId();
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        //  是否提交题目
        Long userId = loginUser.getId();
        //  每个用户串行提交
        //  锁必要包裹住事务方法
        QuestionSubmit questionSubmit = new QuestionSubmit();
        questionSubmit = questionSubmit.builder()
                .userId(userId)
                .questionId(questionId)
                .code(questionSubmitAddRequest.getCode())
                //  语言枚举
                .language(language)
                //  状态枚举
                .status(QuestionSubmitStatusEnum.WATING.getValue())
                .judgeInfo("{}")
                .build();
        boolean save = this.save(questionSubmit);
        if (!save) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return questionSubmit.getId();

        /**
         * 这里加锁的原因是为了让用户再多次点击时只再数据库中生成一条记录
         * TODO 后续最好优化为线程的方式实现
         */
//        QuestionSubmitService questionSubmitService = (QuestionSubmitService) AopContext.currentProxy();
//        synchronized (String.valueOf(userId).intern()) {
//            return questionSubmitService.doQuestionSubmitInner(userId, questionSubmitAddRequest);
//        }
    }

    /**
     * 获取查询包装类（用户根据哪些字段查询，根据前端传来的请求对象，得到 mybatis 框架支持的查询 QueryWrapper 类）
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    @Override
    public QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest) {
        QueryWrapper<QuestionSubmit> queryWrapper = new QueryWrapper<>();
        if (questionSubmitQueryRequest == null) {
            return queryWrapper;
        }
        String language = questionSubmitQueryRequest.getLanguage();
        Integer status = questionSubmitQueryRequest.getStatus();
        Long questionId = questionSubmitQueryRequest.getQuestionId();
        Long userId = questionSubmitQueryRequest.getUserId();
        String sortField = questionSubmitQueryRequest.getSortField();
        String sortOrder = questionSubmitQueryRequest.getSortOrder();

        // 拼接查询条件
        queryWrapper.eq(StringUtils.isNotBlank(language), "language", language);
        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "userId", userId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(questionId), "questionId", questionId);
        queryWrapper.eq(QuestionSubmitStatusEnum.getEnumByValue(status) != null, "status", status);
        queryWrapper.eq("isDelete", false);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }

    /**
     * 获取提交信息
     * @param questionSubmit
     * @param loginUser
     * @return
     */
    @Override
    public QuestionSubmitVo getQuestionSubmitVo(QuestionSubmit questionSubmit, User loginUser) {
        QuestionSubmitVo questionSubmitVo = QuestionSubmitVo.objToVo(questionSubmit);
        // 脱敏：仅本人和管理员能看见自己（提交 userId 和登录用户 id 不同）提交的代码
        long userId = loginUser.getId();
        // 处理脱敏
        if (userId != questionSubmit.getUserId() && !userService.isAdmin(loginUser)) {
            questionSubmitVo.setCode(null);
        }
        return questionSubmitVo;
    }

    /**
     * 分页获取提交信息
     * @param questionSubmitPage
     * @param loginUser
     * @return
     */
    @Override
    public Page<QuestionSubmitVo> getQuestionSubmitVoPage(Page<QuestionSubmit> questionSubmitPage, User loginUser) {
        List<QuestionSubmit> questionSubmitList = questionSubmitPage.getRecords();
        Page<QuestionSubmitVo> questinoSubmitVoPage = new Page<>(questionSubmitPage.getCurrent(), questionSubmitPage.getSize(), questionSubmitPage.getTotal());
        if (CollectionUtils.isEmpty(questionSubmitList)) {
            return questinoSubmitVoPage;
        }
        List<QuestionSubmitVo> QuestionSubmitVoList = questionSubmitList.stream()
                .map(questionSubmit -> getQuestionSubmitVo(questionSubmit, loginUser))
                .collect(Collectors.toList());
        questinoSubmitVoPage.setRecords(QuestionSubmitVoList);
        return questinoSubmitVoPage;
    }
}
