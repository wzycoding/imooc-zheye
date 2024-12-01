<template>
    <div class="file-upload">
      <div class="file-upload-container" @click.prevent="triggerUpload" v-bind="$attrs">
        <slot v-if="fileStatus === 'loading'" name="loading">
          <button class="btn btn-primary" disabled>正在上传...</button>
        </slot>
        <slot v-else-if="fileStatus === 'uploaded'" name="uploaded">
          <button class="btn btn-primary">上传成功</button>
        </slot>
        <slot v-else name="default">
          <button class="btn btn-primary">点击上传</button>
        </slot>
      </div>
      <input type="file" class="file-input d-none" ref="fileInput" @change="handleFileChange"/>
    </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue'

export default defineComponent({
  name: 'Uploader',
  props: {
    action: {

    },
    beforeUpload: {

    },
    uploaded: {

    }
  },
  setup () {
    const fileStatus = ref(null)
    const triggerUpload = () => {
      console.log(123)
    }
    const handleFileChange = () => {
      console.log(123)
    }
    return {
      triggerUpload,
      fileStatus,
      handleFileChange
    }
  }
})
</script>

<style lang="css">
</style>
