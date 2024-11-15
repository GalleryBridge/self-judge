<template>
  <a-row
    id="global-header"
    style="margin-bottom: 16px"
    align="center"
    :wrap="false"
  >
    <a-col flex="auto">
      <div>
        <a-menu
          mode="horizontal"
          :selected-keys="selectedKeys"
          @menu-item-click="doMenuClick"
        >
          <a-menu-item
            key="0"
            :style="{ padding: 0, marginRight: '38px' }"
            disabled
          >
            <div :style="{ display: 'flex' }">
              <div class="title">判题</div>
            </div>
          </a-menu-item>
          <a-menu-item
            v-for="item in visibleRoutes"
            :key="item.path"
            class="home"
          >
            {{ item.name }}
          </a-menu-item>
        </a-menu>
      </div>
    </a-col>
    <a-col flex="100px">
      <div class="userTag">
        {{ store.state.user?.loginUser?.userName ?? "未登录" }}
      </div>
    </a-col>
  </a-row>
</template>

<script setup lang="ts">
import { routes } from "@/router/routes";
import { useRouter } from "vue-router";
import { computed, ref } from "vue";
import { useStore } from "vuex";
import checkAccess from "@/access/checkAccess";

const router = useRouter();

const selectedKeys = ref(["/"]);
const store = useStore();

//  要展示的路由
//  这里出现了 登录有没有刷线权限的问题 所以使用 computed来完善
const visibleRoutes = computed(() => {
  return routes.filter((item, index) => {
    if (item.meta?.hideInMenu) {
      return false;
    }
    //  根据权限过滤菜单
    if (
      !checkAccess(store.state.user?.loginUser, item?.meta?.access as string)
    ) {
      return false;
    }
    return true;
  });
});

//  通过监听路由的方式 实现记录当前页面
//  根据路由地址改变导航栏的高亮
/**
 * 参数 to 你要跳转到哪个路由
 */
router.afterEach((to, from, failure) => {
  selectedKeys.value = [to.path];
});

const doMenuClick = (key: string) => {
  router.push({
    path: key,
  });
};
</script>

<style scoped>
.title {
  color: black;
  font-size: 24px;
  box-shadow: #eee 1px 1px 5px;
}

.home {
  color: black;
  font-size: 20px;
}

.userTag {
  color: black;
  font-size: 20px;
  margin-right: 20px;
}
</style>
