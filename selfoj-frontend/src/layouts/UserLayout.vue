<template>
  <div id="user-layout">
    <a-layout style="min-height: 100vh">
      <a-layout-header class="header"> 用户布局</a-layout-header>
      <a-layout-content class="content">
        <router-view />
      </a-layout-content>
      <a-layout-footer class="footer">
        <a href="https://github.com/GalleryBridge" target="_blank"
          >GalleryBridge</a
        >
      </a-layout-footer>
    </a-layout>
  </div>
</template>
<script setup lang="ts"></script>

<style scoped>
#user-layout {
}

#user-layout .header {
  margin-top: 16px;
}

#user-layout .content {
  background: linear-gradient(to right, #eee, #fff);
  margin-top: 16px;
  padding: 20px;
}

#user-layout .footer {
  background: #efefef;
  margin-top: 16px;
  padding: 16px;
  position: sticky;
  bottom: 0;
  left: 0;
  right: 0;
  text-align: center;
}
</style>
