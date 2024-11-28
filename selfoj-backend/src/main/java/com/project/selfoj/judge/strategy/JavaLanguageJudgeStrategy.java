package com.project.selfoj.judge.strategy;

import cn.hutool.json.JSONUtil;
import com.project.selfoj.model.dto.question.JudgeCase;
import com.project.selfoj.model.dto.question.JudgeConfig;
import com.project.selfoj.model.dto.questionsubmit.JudgeInfo;
import com.project.selfoj.model.entity.Question;
import com.project.selfoj.model.enums.JudgeInfoMessageEnum;

import java.util.List;

/**
 * @Author: Laidor
 * @Description: Java程序策略
 * @Date:2024/11/28 下午 08:39
 */
public class JavaLanguageJudgeStrategy implements JudgeStrategy {

    /**
     * 执行判题
     *
     * @param judgeContext
     * @return
     */
    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext){
        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();
        List<String> inputList = judgeContext.getInputList();
        List<String> outputList = judgeContext.getOutputList();
        Question question = judgeContext.getQuestion();
        List<JudgeCase> judgeCaselist = judgeContext.getJudgeCaseList();
        JudgeInfoMessageEnum judgeInfoMessageEnum = JudgeInfoMessageEnum.ACCEPTED;
        Long memory = judgeInfo.getMemory();
        Long time = judgeInfo.getTime();
        JudgeInfo judgeInfoResponse = new JudgeInfo();
        judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
        judgeInfoResponse.setMemory(memory);
        judgeInfoResponse.setTime(time);

        if (outputList.size() != inputList.size()){
            judgeInfoMessageEnum = judgeInfoMessageEnum.WRONG_ANSWER;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }
        //  判断每一项输出是否相等
        for (int i = 0; i < judgeCaselist.size(); i++) {
            JudgeCase judgeCase = judgeCaselist.get(i);
            if (!judgeCase.getOutput().equals(outputList.get(i))){
                judgeInfoMessageEnum = judgeInfoMessageEnum.WRONG_ANSWER;
                judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
                return judgeInfoResponse;
            }
        }
        //  判题策略
        String judgeConfigStr = question.getJudgeConfig();
        JudgeConfig judgeConfig = JSONUtil.toBean(judgeConfigStr, JudgeConfig.class);
        //  Java需要额外执行10s
        long JAVA_PROGRAM_TIME_COST = 10000;
        Long memoryLimit = judgeConfig.getMemoryLimit();
        Long timeLimit = judgeConfig.getTimeLimit();
        if (memory > memoryLimit){
            judgeInfoMessageEnum = judgeInfoMessageEnum.MEMORY_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;

        }
        if ((time - JAVA_PROGRAM_TIME_COST) > timeLimit) {
            judgeInfoMessageEnum = judgeInfoMessageEnum.TIME_LIMIT_EXCEEDED;
            judgeInfoResponse.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfoResponse;
        }

        return judgeInfoResponse;
    }
}
