package com.project.selfoj.model.vo;

import cn.hutool.json.JSONUtil;
import com.project.selfoj.model.dto.question.JudgeConfig;
import com.project.selfoj.model.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: Laidor
 * @Description: 题目视图对象
 * @Date:2024/11/16 上午 12:16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionVo implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表
     */
    private List<String> tags;

    /**
     * 题目提交数
     */
    private Integer submitNum;

    /**
     * 题目通过数
     */
    private Integer acceptedNum;

    /**
     * 判题配置（json 对象）
     */
    private JudgeConfig judgeConfig;

    /**
     * 点赞数
     */
    private Integer thumbNum;

    /**
     * 收藏数
     */
    private Integer favourNum;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建题目人的信息
     */
    private UserVO userVO;

    /**
     * 包装类转对象
     *
     * @param QuestionVo
     * @return
     */
    public static Question voToObj(QuestionVo QuestionVo) {
        if (QuestionVo == null) {
            return null;
        }
        Question question = new Question();
        BeanUtils.copyProperties(QuestionVo, question);
        List<String> tagList = QuestionVo.getTags();
        if (tagList != null) {
            question.setTags(JSONUtil.toJsonStr(tagList));
        }
        JudgeConfig voJudgeConfig = QuestionVo.getJudgeConfig();
        if (voJudgeConfig != null) {
            question.setJudgeConfig(JSONUtil.toJsonStr(voJudgeConfig));
        }
        return question;
    }

    /**
     * 对象转包装类
     *
     * @param question
     * @return
     */
    public static QuestionVo objToVo(Question question) {
        if (question == null) {
            return null;
        }
        QuestionVo QuestionVo = new QuestionVo();
        BeanUtils.copyProperties(question, QuestionVo);
        List<String> tagList = JSONUtil.toList(question.getTags(), String.class);
        QuestionVo.setTags(tagList);
        String judgeConfigStr = question.getJudgeConfig();
        QuestionVo.setJudgeConfig(JSONUtil.toBean(judgeConfigStr, JudgeConfig.class));
        return QuestionVo;
    }

    private static final long serialVersionUID = 1L;

}
