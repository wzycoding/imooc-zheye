<template>
    <div class="file-upload">
      <div class="file-upload-container" @click.prevent="triggerUpload" v-bind="$attrs">
        <slot v-if="fileStatus === 'loading'" name="loading">
          <button class="btn btn-primary" disabled>正在上传...</button>
        </slot>
        <slot v-else-if="fileStatus === 'success'" name="uploaded">
          <button class="btn btn-primary">上传成功</button>
        </slot>
        <slot v-else name="default">
          <button class="btn btn-primary">点击上传</button>
        </slot>
      </div>
      <input ref="fileInput" type="file" class="file-input d-none" @change="handleFileChange"/>
    </div>
</template>

<script lang="ts">
import axios from 'axios'
import { defineComponent, PropType, ref } from 'vue'

type UploadStatus = 'ready' | 'loading' | 'success' | 'error'
type CheckFunction = (file: File) => boolean
export default defineComponent({
  name: 'Uploader',
  props: {
    action: {
      type: String,
      required: true
    },
    beforeUpload: {
      type: Function as PropType<CheckFunction>
    },
    uploaded: {
      type: Object
    }
  },
  setup (props) {
    const fileInput = ref<null | HTMLInputElement>()
    const fileStatus = ref<UploadStatus>(props.uploaded ? 'success' : 'ready')
    const triggerUpload = () => {
      console.log(456)
      console.log(fileInput.value)
      if (fileInput.value) {
        fileInput.value.click()
      }
    }
    const handleFileChange = (e: Event) => {
      const currentTarget = e.target as HTMLInputElement
      const files = currentTarget.files

      if (files) {
        const uploadedFiles = Array.from(files)
        const uploadedFile = uploadedFiles[0]
        if (props.beforeUpload) {
          const result = props.beforeUpload(uploadedFile)
          if (!result) {
            return
          }
        }
        fileStatus.value = 'loading'
        const formData = new FormData()
        formData.append('file', uploadedFile)
        axios.post(props.action, formData, {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        }).then((resp) => {
          console.log(resp)
        })
      }
      console.log(123)
    }
    return {
      triggerUpload,
      fileStatus,
      handleFileChange,
      fileInput
    }
  }
})
</script>

<style lang="css">
</style>
