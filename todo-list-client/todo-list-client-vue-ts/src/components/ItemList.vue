<template>
  <el-table
    :data="list"
    border
    style="width: 100%"
  >
    <el-table-column
      prop="title"
      label="标题"
    />
    <el-table-column
      prop="content"
      label="备注"
      ><template #default="scope">
        <span>{{ scope.row.content || '-' }}</span>
      </template></el-table-column
    >
    
    <el-table-column
      prop="status"
      label="状态"
      width="90"
    >
      <template #default="scope">
        <template v-if="scope.row.status == ItemStatusEnum.delete">
          <el-popconfirm
            title="改为【待处理】状态?"
            width="180"
            @confirm="handleStatusChange(scope.row, ItemStatusEnum.wait)"
          >
            <template #reference>
              <el-tag
                type="danger"
                style="cursor: pointer"
                >已删除</el-tag
              >
            </template>
          </el-popconfirm>
        </template>

        <template v-else-if="scope.row.status == ItemStatusEnum.wait">
          <el-popconfirm
            title="改为【已完成】状态?"
            width="180"
            @confirm="handleStatusChange(scope.row, ItemStatusEnum.success)"
          >
            <template #reference>
              <el-tag
                type="warning"
                style="cursor: pointer"
                >待处理</el-tag
              >
            </template>
          </el-popconfirm>
        </template>

        <template v-else-if="scope.row.status == ItemStatusEnum.success">
          <el-popconfirm
            title="改为【待处理】状态?"
            width="180"
            @confirm="handleStatusChange(scope.row, ItemStatusEnum.wait)"
          >
            <template #reference>
              <el-tag
                type="success"
                style="cursor: pointer"
                >已完成</el-tag
              >
            </template>
          </el-popconfirm>
        </template>

        <template v-else>-</template>
      </template>
    </el-table-column>

    <el-table-column
      prop="create_time"
      label="创建时间"
      width="180"
    />

    <el-table-column
      prop="update_time"
      label="更新时间"
      width="180"
      center
    />

    <el-table-column
      prop="update_time"
      label="编辑"
      width="80"
    >
      <template #default="scope">
        <el-button
          type="primary"
          @click="handleEditClick(scope.row)"
          :disabled="props.status == ItemStatusEnum.delete"
        >
          <el-icon style="vertical-align: middle">
            <Edit />
          </el-icon>
        </el-button>
      </template>
    </el-table-column>

    <el-table-column
      prop="update_time"
      label="移除"
      width="80"
    >
      <template #default="scope">
        <el-button
          type="danger"
          @click="handleDelete(scope.row)"
          :disabled="props.status == ItemStatusEnum.delete"
        >
          <el-icon style="vertical-align: middle">
            <Delete />
          </el-icon>
        </el-button>
      </template>
    </el-table-column>
  </el-table>

  <div style="margin: 20px auto">
    <el-pagination
      style="justify-content: center"
      background
      layout="prev, pager, next, total"
      v-model:current-page="page"
      :page-size="size"
      :total="total"
      @current-change="handleCurrentChange"
    />
  </div>

  <ItemFormDialog
    :row="currentRow"
    v-model:visible="visible"
    @on-success="resetData"
  ></ItemFormDialog>
</template>

<script lang="ts" setup>
import { onMounted, ref } from 'vue'
import { getItemList, deleteItemById, updateItemById } from '../http/api'
import { ItemModel } from '../models/ItemModel'
import { ElMessage } from 'element-plus'
import ItemFormDialog from './ItemFormDialog.vue'
import { ItemStatusEnum } from '../enums/ItemStatusEnum'

const props = defineProps<{
  status?: number
}>()

const options = [
  {
    label: '待处理',
    value: ItemStatusEnum.wait,
  },
  {
    label: '已完成',
    value: ItemStatusEnum.success,
  },
]

const currentRow = ref<ItemModel>()
const visible = ref(false)

const handleEditClick = (row: ItemModel) => {
  currentRow.value = row
  visible.value = true
}

const list = ref<Array<ItemModel>>()
const total = ref(0)
const page = ref(1)
const size = ref(10)

const resetData = async () => {
  page.value = 1
  list.value = []
  total.value = 0
  getData()
}

const getData = async () => {
  const params = {
    status: props.status,
    page: page.value,
    size: size.value,
  }

  const res = await getItemList(params)

  console.log(res)

  list.value = res.data.list
  total.value = res.data.total
}

const handleCurrentChange = () => {
  getData()
}
onMounted(() => {
  resetData()
})

const handleDelete = async (row: ItemModel) => {
  console.log(row.item_id)
  const res = await deleteItemById({ item_id: row.item_id })

  if (res.code == 0) {
    ElMessage({
      message: '保存成功',
      type: 'success',
    })
    resetData()
  } else {
    ElMessage({
      message: res.msg,
      type: 'error',
    })
  }
}

const handleStatusChange = async (row: ItemModel, val: any) => {
  console.log(row.item_id)

  const res = await updateItemById({ item_id: row.item_id, status: val })

  if (res.code == 0) {
    ElMessage({
      message: '保存成功',
      type: 'success',
    })
    // resetData()
    row.status = val
  } else {
    ElMessage({
      message: res.msg,
      type: 'error',
    })
  }
}
</script>
