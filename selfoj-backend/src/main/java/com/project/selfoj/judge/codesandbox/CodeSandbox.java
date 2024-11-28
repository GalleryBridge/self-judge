package com.project.selfoj.judge.codesandbox;

import com.project.selfoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.project.selfoj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * @Author: Laidor
 * @Description: 代码沙箱接口
 * @Date:2024/11/27 下午 10:16
 */
public interface CodeSandbox {

    /**
     * 执行代码
     *
     * @param executeCodeRequest
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);

    /**
     * TODO 查看执行的代码状态
     */
}
