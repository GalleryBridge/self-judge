package com.project.selfoj.judge.codesandbox;

import com.project.selfoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.project.selfoj.judge.codesandbox.model.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Laidor
 * @Description: 代码沙箱增强
 * @Date:2024/11/27 下午 10:45
 */
@Slf4j
public class CodeSandboxProxy implements CodeSandbox{

    private final CodeSandbox codeSandbox;

    public CodeSandboxProxy(CodeSandbox codeSandbox) {
        this.codeSandbox = codeSandbox;
    }

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest request) {
        log.info("执行代码请求：{}", request.toString());
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(request);
        log.info("执行代码响应：{}", executeCodeResponse.toString());
        return executeCodeResponse;
    }
}
