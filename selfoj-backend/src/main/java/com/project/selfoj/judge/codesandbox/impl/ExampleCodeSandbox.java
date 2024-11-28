package com.project.selfoj.judge.codesandbox.impl;

import com.project.selfoj.judge.codesandbox.CodeSandbox;
import com.project.selfoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.project.selfoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.project.selfoj.model.dto.questionsubmit.JudgeInfo;
import com.project.selfoj.model.enums.JudgeInfoMessageEnum;
import com.project.selfoj.model.enums.QuestionSubmitStatusEnum;

import java.util.List;

/**
 * @Author: Laidor
 * @Description: 示例代码沙箱
 * @Date:2024/11/27 下午 10:24
 */
public class ExampleCodeSandbox implements CodeSandbox {

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        List<String> inputList = executeCodeRequest.getInputList();
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutputList(inputList);
        executeCodeResponse.setMessage("测试执行成功");
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.SUCCESS.getValue());
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getText());
        judgeInfo.setMemory(100L);
        judgeInfo.setTime(100L);
        executeCodeResponse.setJudgeInfo(judgeInfo);

        return executeCodeResponse;
    }
}
