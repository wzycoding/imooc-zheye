<template>
    <div class="create-post-page container">
      <h4>{{ isEditMode ? "编辑文章" : "新建文章" }}</h4>
      {{ titleVal  }}
      <uploader
        action="/upload/"
        :beforeUpload="uploadCheck"
        @file-uploaded-success="onFileUploadedSuccess"
        class="d-flex align-items-center justify-content-center bg-light text-secondary w-100 my-4"
      >
        <h2>点击上传头图</h2>
        <template #loading>
          <h2>正在上传</h2>
        </template>
        <template #uploaded="dataProps">
          <div class="uploaded-area">
            <img :src="dataProps.uploadedData.data.url" />
            <h3>点击重新上传</h3>
          </div>
        </template>
      </uploader>
      <ValidateForm @form-submit="onFormSubmit">
        <div class="mb-3">
          <label class="form-label">文章标题：</label>
          <ValidateInput
            :rules="titleRules"
            v-model="titleVal"
            placeHolder="请输入文章标题"
            type="text">
          </ValidateInput>
        </div>
        <div class="mb-3">
          <label class="form-label">文章详情：</label>
          <ValidateInput
            rows="10"
            :rules="contentRules"
            v-model="contentVal"
            tag="textarea"
            placeHolder="请输入文章详情"
            type="text">
          </ValidateInput>
        </div>
        <template #submit>
          <button class="btn btn-primary btn-large">{{ isEditMode ? '更新文章' : '发表文章' }}</button>
        </template>
      </ValidateForm>
    </div>
</template>

<script lang="ts">
import ValidateForm from '@/base/ValidateForm.vue'
import ValidateInput, { RuleProps } from '@/base/ValidateInput.vue'
import { defineComponent, onMounted, ref } from 'vue'
import { GlobalDataProps, ImageProps, PostProps, ResponseType } from '@/store'
import { useStore } from 'vuex'
import { useRoute, useRouter } from 'vue-router'
import Uploader from '@/base/Uploader.vue'
import { beforeUploadCheck } from '@/helper'
import createMessage from '@/components/createMessage'

export default defineComponent({
  name: 'CreatePost',
  components: {
    ValidateForm,
    ValidateInput,
    Uploader
  },
  props: {
  },
  setup () {
    const router = useRouter()
    const route = useRoute()
    const store = useStore<GlobalDataProps>()
    const titleVal = ref('')
    const contentVal = ref('')
    const queryId = route.query.id
    const isEditMode = !!queryId // 两个!!转换为 boolean

    const uploadedData = ref()
    let imageId = 0
    const titleRules: RuleProps = [
      { type: 'required', message: '文章标题不能为空' }
    ]
    const contentRules: RuleProps = [
      { type: 'required', message: '文章内容不能为空' }
    ]

    onMounted(() => {
      if (isEditMode) {
        store.dispatch('fetchPostDetial', queryId).then((rawData: ResponseType<PostProps>) => {
          const currentPost = rawData.data
          console.log(currentPost)
          const { image, title, content } = currentPost
          titleVal.value = title
          contentVal.value = content || ''

          console.log(titleVal.value)
          if (image) {
            uploadedData.value = { data: image }
          }
        })
      }
    })
    const onFormSubmit = (result: boolean) => {
      console.log(result)
      if (result) {
        const { columnId } = store.state.user
        if (columnId) {
          const newPost:PostProps = {
            title: titleVal.value,
            content: contentVal.value,
            columnId
          }
          if (imageId) {
            newPost.image = imageId
          }
          store.dispatch('createPost', newPost)
          createMessage('发布成功，两秒后跳转专栏页面', 'success')
          setTimeout(() => {
            router.push({ path: `/column/${columnId}` })
          }, 2000)
        }
      }
    }
    const onFileUploadedSuccess = (resp: ResponseType<ImageProps>) => {
      console.log(resp)
      if (resp.data.id) {
        imageId = resp.data.id
      }
    }

    const uploadCheck = (file: File) => {
      const result = beforeUploadCheck(file, { format: ['image/jpeg', 'image/png'], size: 1 })
      const { passed, error } = result
      if (error === 'format') {
        createMessage('上传图片格式只能为 JPG/PNG', 'error')
      }
      if (error === 'size') {
        createMessage('上传图片大小不能超过1MB!', 'error')
      }
      return passed
    }
    return {
      titleVal,
      contentVal,
      isEditMode,
      onFormSubmit,
      titleRules,
      contentRules,
      onFileUploadedSuccess,
      uploadCheck
    }
  }
})
</script>

<style>
.create-post-page .file-upload-container {
  height: 200px;
  cursor: pointer;
  overflow: hidden;
}
.create-post-page .file-upload-container img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.uploaded-area {
  position: relative;
}
.uploaded-area:hover h3 {
  display: block;
}
.uploaded-area h3 {
  display: none;
  position: absolute;
  color: #999;
  text-align: center;
  width: 100%;
  top: 50%;
}
</style>
