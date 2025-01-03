import { RouteRecordRaw } from "vue-router";
import AccessEnum from "@/access/accessEnum";
import NoAuthView from "@/views/NoAuthView.vue";
import LoginView from "@/views/user/LoginView.vue";
import RegisterView from "@/views/user/RegisterView.vue";
import UserLayout from "@/layouts/UserLayout.vue";
import AddQuestionView from "@/views/question/AddQuestionView.vue";
import ManageQuestionView from "@/views/question/ManageQuestionView.vue";
import QuestionsVo from "@/views/question/QuestionsVo.vue";
import ViewQuestionsView from "@/views/question/ViewQuestionsView.vue";

export const routes: Array<RouteRecordRaw> = [
  {
    path: "/user",
    name: "登录",
    component: UserLayout,
    children: [
      {
        path: "/user/login",
        name: "登录",
        component: LoginView,
      },
      {
        path: "/user/register",
        name: "注册",
        component: RegisterView,
      },
    ],
    meta: {
      hideInMenu: true,
    },
  },
  {
    path: "/",
    name: "主页",
    component: QuestionsVo,
  },
  {
    path: "/questions",
    name: "浏览题目",
    component: QuestionsVo,
  },
  {
    path: "/questions/view/:id",
    name: "在线做题",
    component: ViewQuestionsView,
    //  在页面中直接以属性的方式获取到路由参数
    props: true,
    meta: {
      access: AccessEnum.USER,
    },
  },
  {
    path: "/question/add",
    name: "创建题目",
    component: AddQuestionView,
    meta: {
      access: AccessEnum.USER,
    },
  },
  {
    path: "/question/update",
    name: "更新题目",
    component: AddQuestionView,
    meta: {
      access: AccessEnum.USER,
      hideInMenu: true,
    },
  },

  {
    path: "/question/manage",
    name: "管理题目",
    component: ManageQuestionView,
    meta: {
      access: AccessEnum.ADMIN,
    },
  },
  {
    path: "/about",
    name: "关于我的",
    component: () => import("@/views/AboutView.vue"),
  },
  {
    path: "/noAuth",
    name: "无权限",
    component: NoAuthView,
    meta: {
      hideInMenu: true,
    },
  },
];
