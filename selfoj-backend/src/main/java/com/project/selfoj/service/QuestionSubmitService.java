package com.project.selfoj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.selfoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.project.selfoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.project.selfoj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.selfoj.model.entity.User;
import com.project.selfoj.model.vo.QuestionSubmitVo;

/**
* @author Laido
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2024-11-15 22:14:35
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {

    /**
     * 提交题目
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);

    /**
     * 获取查询条件
     * @param questionSubmitQueryRequest
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);

    /**
     * 获取题目封装
     * @param questionSubmit
     * @param loginUser
     * @return
     */
    QuestionSubmitVo getQuestionSubmitVo(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取题目封装
     * @param questionSubmitPage
     * @param loginUser
     * @return
     */
    Page<QuestionSubmitVo> getQuestionSubmitVoPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);


}
