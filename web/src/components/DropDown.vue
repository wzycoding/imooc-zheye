<template>
    <div class="dropdown" ref="dropdownRef">
      <li class="list-inline-item">
        <a href="#" class="btn btn-outline-light my-2 dropdown-toggle" @click.prevent="toggleOpen">{{ title }}</a>
      </li>
      <ul class="dropdown-menu" :style="{display: 'block'}" v-if="isOpen">
        <slot></slot>
      </ul>
    </div>
</template>

<script lang="ts">
import { defineComponent, ref, watch } from 'vue'
import useClickOutside from '@/hooks/useClickOutside'

export default defineComponent({
  name: 'DropDown',
  props: {
    title: {
      type: String,
      required: true
    }
  },
  components: {
  },
  setup () {
    const isOpen = ref(false)
    const dropdownRef = ref<null | HTMLElement>(null)
    const toggleOpen = () => {
      isOpen.value = !isOpen.value
    }
    const isClickOutSide = useClickOutside(dropdownRef)

    watch(isClickOutSide, () => {
      if (isClickOutSide && isOpen) {
        isOpen.value = false
      }
    })

    return {
      isOpen,
      toggleOpen,
      // 跟页面上的ref属性完全一样
      dropdownRef
    }
  }
})
</script>

<style lang="css">
</style>
