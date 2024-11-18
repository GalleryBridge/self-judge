package com.project.selfoj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.selfoj.model.dto.question.QuestionQueryRequest;
import com.project.selfoj.model.entity.Question;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.selfoj.model.vo.QuestionVo;

import javax.servlet.http.HttpServletRequest;

/**
* @author Laido
* @description 针对表【question(题目)】的数据库操作Service
* @createDate 2024-11-15 22:13:55
*/
public interface QuestionService extends IService<Question> {
    /**
     * 校验
     * @param question
     * @param add
     */
    void validQuestion(Question question, boolean add);

    /**
     * 获取查询条件
     * @param questionQueryRequest
     * @return
     */
    QueryWrapper<Question> getQueryWrapper(QuestionQueryRequest questionQueryRequest);

    /**
     * 获取题目封装
     * @param question
     * @param request
     * @return
     */
    QuestionVo getQuestionVo(Question question, HttpServletRequest request);

    /**
     * 分页获取题目封装
     * @param questionPage
     * @param request
     * @return
     */
    Page<QuestionVo> getQuestionVoPage(Page<Question> questionPage, HttpServletRequest request);

}
