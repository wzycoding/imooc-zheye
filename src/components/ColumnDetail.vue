<template>
    <pre>{{ route }}</pre>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import { useRoute } from 'vue-router'

export default defineComponent({
  name: 'ColumnDetail',
  props: {
  },
  setup () {
    const route = useRoute()
    return {
      route
    }
  }
})
</script>

<style lang="css">
</style>
