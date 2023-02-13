<template>
  <el-table
    :data="list"
    border
    style="width: 100%"
  >
    <el-table-column
      prop="title"
      label="标题"
      width="180"
    />
    <el-table-column
      prop="content"
      label="备注"
      width="180"
    />
    <el-table-column
      prop="address"
      label="状态"
    />

    <el-table-column
      prop="create_time"
      label="创建时间"
    />

    <el-table-column
      prop="update_time"
      label="更新时间"
    />

    <el-table-column
      prop="update_time"
      label="编辑"
    ></el-table-column>

    <el-table-column
      prop="update_time"
      label="移除"
    ></el-table-column>
  </el-table>
</template>

<script lang="ts" setup>
import { onMounted, ref } from 'vue'
import { getItemList } from '../http/api.js'

const list = ref(null)

onMounted(async () => {
  const data = await getItemList()
  list.value = data.list
})
</script>
