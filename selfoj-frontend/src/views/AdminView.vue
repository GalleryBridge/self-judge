<template>
  <div class="home">
    <div>管理员可见</div>
    <HelloWorld msg="Welcome to Your Vue.js + TypeScript App" />
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import HelloWorld from "@/components/HelloWorld.vue"; // @ is an alias to /src

export default defineComponent({
  name: "AdminView",
  components: {
    HelloWorld,
  },
});
</script>
