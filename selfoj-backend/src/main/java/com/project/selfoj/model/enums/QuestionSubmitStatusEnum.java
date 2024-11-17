package com.project.selfoj.model.enums;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Laidor
 * @Description: 提交状态枚举
 * @Date:2024/11/17 下午 05:18
 */
public enum QuestionSubmitStatusEnum {

    //  0 - 待判题 1 - 判题中 2 - 成功 3 - 失败

    WATING("待判题", 0),

    JUDGING("判题中", 1),

    SUCCESS("成功", 2),

    FAILED("失败", 3);

    private final String text;

    private final Integer value;

    QuestionSubmitStatusEnum(String text, Integer value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return
     */
    public static List<Integer> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value
     * @return
     */
    public static QuestionSubmitStatusEnum getEnumByValue(Integer value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (QuestionSubmitStatusEnum item : QuestionSubmitStatusEnum.values()) {
            if (item.value.toString().equals(value)) {
                return item;
            }
        }
        return null;
    }

    public Integer getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

}
