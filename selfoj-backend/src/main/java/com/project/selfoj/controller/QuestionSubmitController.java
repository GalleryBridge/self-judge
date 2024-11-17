package com.project.selfoj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.selfoj.common.BaseResponse;
import com.project.selfoj.common.ErrorCode;
import com.project.selfoj.common.ResultUtils;
import com.project.selfoj.exception.BusinessException;
import com.project.selfoj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.project.selfoj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.project.selfoj.model.entity.QuestionSubmit;
import com.project.selfoj.model.entity.User;
import com.project.selfoj.model.vo.QuestionSubmitVo;
import com.project.selfoj.service.QuestionSubmitService;
import com.project.selfoj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Laidor
 * @Description: 题目提交
 * @Date:2024/11/15 下午 11:42
 */
@Slf4j
@RestController
@RequestMapping("/submit")
public class QuestionSubmitController {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    public UserService userService;

    /**
     * 提交题目
     *
     * @param questionSubmitAddRequest
     * @param request
     * @return
     */
    @PostMapping
    public BaseResponse<Long> doQuestionSubmit(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest, HttpServletRequest request) {
        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //  登录才能提交
        User loginUser = userService.getLoginUser(request);
        return ResultUtils.success(questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser));

    }

    //  获取题目提交详情
    //  分页获取题目提交列表 (非管理员 只能看到公开信息)
    @PostMapping("/list/page")
    public BaseResponse<Page<QuestionSubmitVo>> listQuestionSubmitByPage(@RequestBody QuestionSubmitQueryRequest questionSubmitQueryRequest, HttpServletRequest request) {
        long current = questionSubmitQueryRequest.getCurrent();
        long size = questionSubmitQueryRequest.getPageSize();
        // 从数据库中查询原始的题目提交分页信息
        Page<QuestionSubmit> questionSubmitPage = questionSubmitService.page(new Page<>(current, size),
                questionSubmitService.getQueryWrapper(questionSubmitQueryRequest));
        final User loginUser = userService.getLoginUser(request);
        // 返回脱敏信息
        return ResultUtils.success(questionSubmitService.getQuestionSubmitVoPage(questionSubmitPage, loginUser));
    }
}
