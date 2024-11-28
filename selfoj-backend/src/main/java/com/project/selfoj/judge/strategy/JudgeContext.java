package com.project.selfoj.judge.strategy;

import com.project.selfoj.model.dto.question.JudgeCase;
import com.project.selfoj.model.dto.questionsubmit.JudgeInfo;
import com.project.selfoj.model.entity.Question;
import com.project.selfoj.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * @Author: Laidor
 * @Description: 上下文 用于定义在策略中传递的参数
 * @Date:2024/11/28 下午 08:41
 */
@Data
public class JudgeContext {

    /**
     * 判题信息
     */
    private JudgeInfo judgeInfo;

    /**
     * 输入用例
     */
    private List<String> inputList;

    /**
     * 输出用例
     */
    private List<String> outputList;

    /**
     * 题目信息
     */
    private Question question;

    /**
     * 判题用例
     */
    private List<JudgeCase> judgeCaseList;

    /**
     * 题目提交信息
     */
    private QuestionSubmit questionSubmit;
}
