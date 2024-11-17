<template>
    <div>
      <ColumnList :list="list"/>
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import ColumnList from '../components/ColumnList.vue'
import { testData } from '../testData'

export default defineComponent({
  name: 'Home',
  components: {
    ColumnList
  },
  props: {
  },
  setup () {
    return {
      list: testData
    }
  }
})
</script>

<style lang="css">
</style>
