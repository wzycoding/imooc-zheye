<template>
    <div class="validate-input-container pb-3">
      <input type="text" class="form-control" id="exampleInputEmail1"
       v-model="inputRef.val"
       :class="{'is-invalid': inputRef.error}"
       @blur="validateInput">

       <span v-if="inputRef.error" class="invalid-feedback">{{ inputRef.message }}</span>
    </div>
</template>

<script lang="ts">
import { defineComponent, PropType, reactive } from 'vue'
const emailReg = /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/

interface RuleProp {
  type: 'required' | 'email'
  message: string
}
export type RulesProp = RuleProp[]
export default defineComponent({
  name: 'ValidateInput',
  props: {
    rules: Array as PropType<RulesProp>
  },
  setup (props) {
    const inputRef = reactive({
      val: '',
      error: false,
      message: ''
    })
    const validateInput = () => {
      console.log(props.rules)
      if (props.rules) {
        const allPassed = props.rules.every(rule => {
          let passed = true
          inputRef.message = rule.message
          switch (rule.type) {
            case 'required':
              console.log(inputRef.val.trim() !== '')
              passed = (inputRef.val.trim() !== '')
              break
            case 'email':
              passed = emailReg.test(inputRef.val)
              break
            default:
              break
          }
          return passed
        })
        inputRef.error = !allPassed
      }
      console.log(inputRef)
    }
    return {
      inputRef,
      validateInput
    }
  }
})
</script>

<style lang="css">
</style>
