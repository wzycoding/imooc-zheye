<template>
    <div class="create-post-page">
      <h4>新建文章</h4>
      <ValidateForm @form-submit="onFormSubmit">
        <div class="mb-3">
          <label class="form-label">文章标题：</label>
          <ValidateInput
            :rules="titleRules" v-model="titleVal"
            placeHolder="请输入文章标题"
            type="text">
          </ValidateInput>
        </div>
        <div class="mb-3">
          <label class="form-label">文章详情：</label>
          <ValidateInput
            rows="10"
            :rules="contentRules" v-model="contentVal" tag="textarea"
            placeHolder="请输入文章详情"
            type="text">
          </ValidateInput>
        </div>
        <template #submit>
          <button class="btn btn-primary btn-large">发表文章</button>
        </template>
      </ValidateForm>
    </div>
</template>

<script lang="ts">
import ValidateForm from '@/components/ValidateForm.vue'
import ValidateInput, { RuleProps } from '@/components/ValidateInput.vue'
import { defineComponent, ref } from 'vue'
import { GlobalDataProps } from '@/store'
import { PostProps } from '@/testData'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default defineComponent({
  name: 'CreatePost',
  components: {
    ValidateForm,
    ValidateInput
  },
  props: {
  },
  setup () {
    const router = useRouter()
    const store = useStore<GlobalDataProps>()
    const titleVal = ref('')
    const contentVal = ref('')
    const titleRules: RuleProps = [
      { type: 'required', message: '文章标题不能为空' }
    ]
    const contentRules: RuleProps = [
      { type: 'required', message: '文章内容不能为空' }
    ]
    const onFormSubmit = (result: boolean) => {
      if (result) {
        const { columnId } = store.state.user
        if (columnId) {
          const newPost:PostProps = {
            id: new Date().getTime(),
            title: titleVal.value,
            content: contentVal.value,
            columnId,
            createdAt: new Date().toLocaleDateString()
          }
          store.commit('createPost', newPost)
          router.push({ name: 'column', params: { id: columnId } })
        }
      }
    }
    return {
      titleVal,
      contentVal,
      onFormSubmit,
      titleRules,
      contentRules
    }
  }
})
</script>

<style lang="css">
</style>
