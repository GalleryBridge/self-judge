package com.project.selfoj.judge;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.project.selfoj.common.ErrorCode;
import com.project.selfoj.exception.BusinessException;
import com.project.selfoj.judge.codesandbox.CodeSandbox;
import com.project.selfoj.judge.codesandbox.CodeSandboxFactory;
import com.project.selfoj.judge.codesandbox.CodeSandboxProxy;
import com.project.selfoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.project.selfoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.project.selfoj.judge.strategy.*;
import com.project.selfoj.model.dto.question.JudgeCase;
import com.project.selfoj.model.dto.questionsubmit.JudgeInfo;
import com.project.selfoj.model.entity.Question;
import com.project.selfoj.model.entity.QuestionSubmit;
import com.project.selfoj.model.enums.QuestionSubmitStatusEnum;
import com.project.selfoj.service.QuestionService;
import com.project.selfoj.service.QuestionSubmitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Laidor
 * @Description: 判题实现类
 * @Date:2024/11/27 下午 11:04
 */
@Service
public class JudgeServiceImpl implements JudgeService{

    @Value("codesandbox.type")
    private String type;

    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private JudgeManager judgeManager;

    @Override
    public QuestionSubmit doJudge(long questionSubmitId) {
        //  传入题目ID 获取题目信息 提交信息
        QuestionSubmit questionSubmit = questionSubmitService.getById(questionSubmitId);
        if (ObjectUtil.isEmpty(questionSubmitId)){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "提交信息不存在");
        }
        Long questionId = questionSubmit.getQuestionId();
        Question question = questionService.getById(questionId);
        if (ObjectUtil.isEmpty(question)){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目信息不存在");
        }
        //  如果不为等待状态
        if (!questionSubmit.getStatus().equals(QuestionSubmitStatusEnum.WATING.getValue())){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "该提交信息已被判题");
        }
        //  更改判题状态 防止重复执行
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.JUDGING.getValue());
        boolean b = questionSubmitService.updateById(questionSubmitUpdate);
        if (!b){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "判题状态更新失败");
        }

        //  执行代码沙箱 获取执行结果
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        codeSandbox = new CodeSandboxProxy(codeSandbox);
        String language = questionSubmit.getLanguage();
        String code = questionSubmit.getCode();
        //  获取测试用例
        String judgeCaseStr = question.getJudgeCase();
        List<JudgeCase> judgeCaselist = JSONUtil.toList(judgeCaseStr, JudgeCase.class);
        List<String> inputList = judgeCaselist.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);

        //  根据执行结果判断题目 是否正确
        /**
         * 判断逻辑
         * 输出结果和数量是否和预期输出数量相等
         * 判断输出和预期输出是否相等
         * 题目限制是否符合要求
         * 其他
         */
        List<String> outputList = executeCodeResponse.getOutputList();

        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setJudgeInfo(executeCodeResponse.getJudgeInfo());
        judgeContext.setInputList(inputList);
        judgeContext.setOutputList(outputList);
        judgeContext.setQuestion(question);
        judgeContext.setJudgeCaseList(judgeCaselist);
        judgeContext.setQuestionSubmit(questionSubmit);
        //  执行判题

        JudgeInfo judgeInfo = judgeManager.doJudge(judgeContext);
        //  修改数据库中的判题结果
        questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.SUCCESS.getValue());
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        boolean result = questionSubmitService.updateById(questionSubmitUpdate);
        if (!result){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "判题状态更新失败");
        }
        return questionSubmitService.getById(questionId);
    }
}
