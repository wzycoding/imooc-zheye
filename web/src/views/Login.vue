<template>
    <div class="login-page mx-auto p-3 w-330">
      <h5 class="my-4 text-center">登录者也账户</h5>
      <ValidateForm @form-submit="onFormSubmit">
      <div class="mb-3">
        <label class="form-label">邮箱地址</label>
        <ValidateInput :rules="emailRules" v-model="emailVal"
          type="text" placeholder="请输入电子邮箱" ref="inputRef"></ValidateInput>
      </div>
      <div class="mb-3">
        <label class="form-label">密码</label>
        <ValidateInput :rules="passwordRules" type="password" v-model="passwordVal" placeholder="请输入密码"/>
      </div>
      <template #submit>
      </template>
    </ValidateForm>
    </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue'
import ValidateInput, { RuleProps } from '../components/ValidateInput.vue'
import ValidateForm from '@/components/ValidateForm.vue'
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'
import { GlobalDataProps } from '../store'
export default defineComponent({
  name: 'ColumnList',
  components: {
    ValidateInput,
    ValidateForm
  },
  setup () {
    const router = useRouter()
    const store = useStore<GlobalDataProps>()
    const passwordVal = ref('')
    const emailVal = ref('')
    const emailRules: RuleProps = [
      { type: 'required', message: '电子邮箱地址不能为空' },
      { type: 'email', message: '请输入正确的电子邮箱格式' }
    ]
    const passwordRules: RuleProps = [
      { type: 'required', message: '密码不能为空' }
    ]
    const onFormSubmit = (result: boolean) => {
      if (result) {
        const payload = {
          email: emailVal.value,
          password: passwordVal.value
        }
        store.dispatch('loginAndFetch', payload).then(data => {
          setTimeout(() => {
            router.push('/')
          }, 1000)
        }).catch(e => {
          console.log(e)
        })
      }
    }

    return {
      passwordVal,
      emailVal,
      emailRules,
      passwordRules,
      onFormSubmit
    }
  }
})
</script>

<style scoped>
.w-330 {
  width: 330px;
}
</style>
