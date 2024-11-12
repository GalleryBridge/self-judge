import { RouteRecordRaw } from "vue-router";
import HomeView from "@/views/HomeView.vue";
import AdminView from "@/views/AdminView.vue";
import NoAuthView from "@/views/NoAuthView.vue";
import AccessEnum from "@/access/accessEnum";

export const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "浏览题目",
    component: HomeView,
  },
  {
    path: "/hide",
    name: "隐藏",
    component: HomeView,
    meta: {
      hideInMenu: true,
    },
  },
  {
    path: "/admin",
    name: "管理员  ",
    component: AdminView,
    meta: {
      access: AccessEnum.ADMIN,
    },
  },
  {
    path: "/about",
    name: "关于我的",
    component: () =>
      import(/* webpackChunkName: "about" */ "@/views/AboutView.vue"),
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
