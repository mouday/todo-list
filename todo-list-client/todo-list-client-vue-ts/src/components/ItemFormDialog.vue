<template>
  <el-dialog
    v-model="dialogVisible"
    :title="title"
    width="450"
    center
    align-center
  >
    <ItemForm
      :row="row"
      @on-success="handleSuccess"
      @on-cancel="handleCancel"
    ></ItemForm>
  </el-dialog>
</template>

<script lang="ts" setup>
import { ref, computed } from 'vue'
import ItemForm from './ItemForm.vue'
import type { ItemModel } from '../models/ItemModel'

const props = defineProps<{
  visible: boolean
  row?: ItemModel
}>()

const emit = defineEmits(['update:visible', 'on-success'])

const title = computed(() => {
  if (props.row) {
    return '编辑'
  } else {
    return '添加'
  }
})

const dialogVisible = computed({
  get() {
    return props.visible
  },
  set(value) {
    emit('update:visible', value)
  },
})

const handleSuccess = () => {
  dialogVisible.value = false
  emit('on-success')
}

const handleCancel = () => {
  dialogVisible.value = false
}
</script>

<style scoped></style>
