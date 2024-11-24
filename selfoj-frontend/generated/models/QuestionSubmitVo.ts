/* generated using openapi-typescript-codegen -- do not edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { JudgeInfo } from './JudgeInfo';
import type { QuestionVo } from './QuestionVo';
import type { UserVO } from './UserVO';
export type QuestionSubmitVo = {
    code?: string;
    createTime?: string;
    id?: number;
    judgeInfo?: JudgeInfo;
    language?: string;
    questionId?: number;
    questionVO?: QuestionVo;
    status?: number;
    updateTime?: string;
    userId?: number;
    userVO?: UserVO;
};

