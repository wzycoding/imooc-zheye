<template>
<teleport to="#back">
  <div
    class="d-flex justify-content-center align-items-center h-100 w-100 loading-container"
    :style="{backgroundColor: background || ''}"
  >
    <div class="loading-content">
      <div class="spinner-border text-primary" role="status">
        <!-- <span class="sr-only">{{ text || '拼命加载中'}}</span> -->
      </div>
      <p v-if="text" class="text-primary small">{{text || '拼命加载中'}}</p>
    </div>
  </div>
</teleport>
</template>

<script lang="ts">
import useDOMCreate from '@/hooks/useDOMCreate'
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'ColumnList',
  props: {
    text: {
      type: String
    },
    background: {
      type: String
    }
  },
  setup () {
    useDOMCreate('back')
  }
})
</script>

<style lang="css">
.loading-container {
  background: rgba(255, 255, 255, .5);
  z-index: 100;
  position: fixed;
  top: 0;
  left: 0;
}
.loading-container {
  text-align: center;
}
</style>
