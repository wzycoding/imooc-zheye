<template>
    <div class="sign-page mx-auto p-3 w-330">
      <h5 class="my-4 text-center">注册者也账户</h5>
      <validate-form @form-submit="onFormSubmit">
      <div class="mb-3">
        <label class="form-label">邮箱地址</label>
        <validate-input
          :rules="emailRules" v-model="formData.email"
          placeholder="请输入邮箱地址"
          type="text"
        />
      </div>
      <div class="mb-3">
        <label class="form-label">昵称</label>
        <validate-input
          :rules="nameRules" v-model="formData.nickname"
          placeholder="请输入昵称"
          type="text"
        />
      </div>
      <div class="mb-3">
        <label class="form-label">密码</label>
        <validate-input
          type="password"
          placeholder="请输入密码"
          :rules="passwordRules"
          v-model="formData.password"
        />
      </div>
      <div class="mb-3">
        <label class="form-label">重复密码</label>
        <validate-input
          type="password"
          placeholder="请再次密码"
          :rules="repeatPasswordRules"
          v-model="formData.repeatPassword"
        />
      </div>
      <template #submit>
        <button type="submit" class="btn btn-primary btn-block btn-large">注册新用户</button>
      </template>
    </validate-form>
    </div>
</template>

<script lang="ts">
import createMessage from '@/components/createMessage'
import ValidateForm from '@/components/ValidateForm.vue'
import ValidateInput, { RuleProps } from '../components/ValidateInput.vue'
import router from '@/router'
import axios from 'axios'
import { defineComponent, reactive } from 'vue'

export default defineComponent({
  name: 'ColumnList',
  props: {
  },
  components: {
    ValidateForm,
    ValidateInput
  },
  setup () {
    const formData = reactive({
      email: '',
      nickname: '',
      password: '',
      repeatPassword: ''
    })
    const nameRules: RuleProps = [
      { type: 'required', message: '昵称不能为空' }
    ]
    const emailRules: RuleProps = [
      { type: 'required', message: '电子邮箱地址不能为空' },
      { type: 'email', message: '请输入正确的电子邮箱格式' }
    ]
    const passwordRules: RuleProps = [
      { type: 'required', message: '密码不能为空' }
    ]
    const repeatPasswordRules: RuleProps = [
      {
        type: 'custom',
        message: '两次密码输入不一致',
        validator: () => formData.password === formData.repeatPassword
      }
    ]
    const onFormSubmit = (result: boolean) => {
      if (result) {
        const payload = {
          email: formData.email,
          password: formData.password,
          nickname: formData.nickname
        }

        axios.post('/user/users', payload).then(data => {
          createMessage('注册成功，正在跳转登录页面', 'success')
          setTimeout(() => {
            router.push('/login')
          }, 2000)
        }).catch(e => {
          console.log(e)
        })
      }
    }
    return {
      nameRules,
      passwordRules,
      emailRules,
      repeatPasswordRules,
      formData,
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
