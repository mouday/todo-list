<template>
  <div class="app-container">
    <h1>Todo List</h1>

    <el-button @click="handleAddClick"
      ><el-icon><Plus /></el-icon>添加</el-button
    >

    <el-tabs
      v-model="activeName"
      class="mo-tabs"
      @tab-click="handleClick"
    >
      <el-tab-pane
        label="全部"
        name="all"
        lazy
        ><ItemList
          v-if="activeName == 'all'"
          ref="ItemListAll"
          :status="ItemStatusEnum.all"
        ></ItemList
      ></el-tab-pane>

      <el-tab-pane
        label="待处理"
        name="wait"
        lazy
        ><ItemList
          v-if="activeName == 'wait'"
          :status="ItemStatusEnum.wait"
        ></ItemList
      ></el-tab-pane>

      <el-tab-pane
        label="已完成"
        name="success"
        lazy
        ><ItemList
          v-if="activeName == 'success'"
          :status="ItemStatusEnum.success"
        ></ItemList
      ></el-tab-pane>

      <el-tab-pane
        label="已删除"
        name="delete"
        lazy
        ><ItemList
          v-if="activeName == 'delete'"
          :status="ItemStatusEnum.delete"
        ></ItemList
      ></el-tab-pane>
    </el-tabs>

    <ItemFormDialog
      v-model:visible="visible"
      @on-success="resetData"
    ></ItemFormDialog>
  </div>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import type { TabsPaneContext } from 'element-plus'
import ItemList from './components/ItemList.vue'
import ItemFormDialog from './components/ItemFormDialog.vue'
import { ItemStatusEnum } from './enums/ItemStatusEnum'

const activeName = ref('all')

const handleClick = (tab: TabsPaneContext, event: Event) => {
  console.log(tab, event)
}

const visible = ref(false)

const handleAddClick = () => {
  visible.value = true
}

const resetData = () => {
  window.location.reload()
}
</script>

<style>
.mo-tabs {
  margin-top: 20px;
}
</style>
