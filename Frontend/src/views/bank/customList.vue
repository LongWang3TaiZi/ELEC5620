<template>
  <div>
    <el-table :data="customer">
      <el-table-column prop="id" label="id"></el-table-column>
      <el-table-column prop="customerName" label="customerName" />
    </el-table>
    <el-pagination
      v-if="total"
      v-model:current-page="queryParams.pageNum"
      :page-size="queryParams.pageSize"
      :size="queryParams.pageNum"
      layout="total, prev, pager, next"
      :total="total"
      @size-change="getPageList"
      @current-change="getPageList"
    />
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { ElTable, ElTableColumn } from 'element-plus'
import { fetchCustomerList } from '@/api/bank'
const customer = ref([])

const queryParams = ref({
  pageSize: 10,
  pageNum: 1,
})
const total = ref(0)
getCustomer()
function getCustomer() {
  fetchCustomerList(queryParams.value).then(res => {
    // console.log(res)
    // if (res.responseCode === 'A1') {
    customer.value = res
    total.value = res.total
    // }
  })
}
</script>
@/api/bank
