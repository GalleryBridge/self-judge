<template>
  <Editor :value="value" :plugins="plugins" @change="handleChange" />
</template>

<script setup lang="ts">
import { ref, withDefaults, defineProps } from "vue";
import { Editor, Viewer } from "@bytemd/vue-next";
import gfm from "@bytemd/plugin-gfm";
import highlight from "@bytemd/plugin-highlight";

interface Props {
  value: string;
  handleChange: (v: string) => void;
}

const plugins = [gfm(), highlight()];

//  这里是父组件操作子组件的方法 是vue3的语法
//  给组件指定初始值 为了提供组件的通用性
const props = withDefaults(defineProps<Props>(), {
  value: () => "",
  handleChange: (v: string) => {
    console.log(v);
  },
});

const value = ref("");

const handleChange = (v: string) => {
  value.value = v;
};
</script>

<style scoped></style>
