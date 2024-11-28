package com.project.selfoj.judge.codesandbox.model;

import com.project.selfoj.model.dto.questionsubmit.JudgeInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: Laidor
 * @Description: 执行代码请求
 * @Date:2024/11/27 下午 10:18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteCodeResponse {

    /**
     * 输入用例
     */
    private List<String> outputList;

    /**
     * 接口信息
     */
    private String message;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 判题信息
     */
    private JudgeInfo judgeInfo;
}
