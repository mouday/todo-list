<template>
  <el-form
    ref="ruleFormRef"
    :model="ruleForm"
    status-icon
    :rules="rules"
    label-width="120px"
    class="demo-ruleForm"
  >
    <el-form-item
      label="标题"
      prop="title"
    >
      <el-input
        v-model="ruleForm.title"
        type="text"
      />
    </el-form-item>

    <el-form-item
      label="内容"
      prop="content"
    >
      <el-input
        v-model="ruleForm.content"
        type="textarea"
      />
    </el-form-item>

    <el-form-item>
      <el-button
        type="primary"
        @click="submitForm(ruleFormRef)"
        >保存</el-button
      >
      <el-button @click="resetForm(ruleFormRef)">重置</el-button>
    </el-form-item>
  </el-form>
</template>

<script lang="ts" setup>
import { onMounted, reactive, ref, defineProps } from 'vue'
import type { FormInstance } from 'element-plus'
import { addItem, updateItemById } from '../http/api'
import type { ItemModel } from '../models/ItemModel'
import type { ResultModel } from '../models/ResultModel'
import { ElMessage } from 'element-plus'
import axios, { AxiosRequestConfig, AxiosResponse } from 'axios'

const props = defineProps<{
  row?: ItemModel
}>()

const emit = defineEmits(['on-success', 'on-cancel'])

const ruleFormRef = ref<FormInstance>()

const ruleForm = reactive({
  title: '',
  content: '',
})

const rules = reactive({
  title: [{ required: true, message: '请填写标题', trigger: 'blur' }],
})

onMounted(() => {
  if (props.row) {
    ruleForm.title = props.row.title
    ruleForm.content = props.row.content
  }
})

const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return

  formEl.validate(async (valid) => {
    if (valid) {
      let res: any

      if (props.row && props.row.item_id) {
        res = await updateItemById({
          ...ruleForm,
          item_id: props.row.item_id,
        })
      } else {
        res = await addItem(ruleForm)
      }

      if (res.code == 0) {
        ElMessage({
          message: '保存成功',
          type: 'success',
        })
        emit('on-success')
      } else {
        ElMessage({
          message: res.msg,
          type: 'error',
        })
      }
    } else {
      console.log('error submit!')
      return false
    }
  })
}

const resetForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.resetFields()
}
</script>
