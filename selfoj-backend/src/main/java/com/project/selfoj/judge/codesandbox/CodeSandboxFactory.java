package com.project.selfoj.judge.codesandbox;

import com.project.selfoj.judge.codesandbox.impl.ExampleCodeSandbox;
import com.project.selfoj.judge.codesandbox.impl.RemoteCodeSandbox;
import com.project.selfoj.judge.codesandbox.impl.ThirdPartyCodeSandbox;

/**
 * @Author: Laidor
 * @Description: 代码沙箱工程
 * @Date:2024/11/27 下午 10:30
 */
public class CodeSandboxFactory {

    public static CodeSandbox newInstance(String type) {
        switch (type) {
            case "example":
                return new ExampleCodeSandbox();
            case "remote":
                return new RemoteCodeSandbox();
            case "thirdParty":
                return new ThirdPartyCodeSandbox();
            default:
                return new ExampleCodeSandbox();
        }
    }
}
