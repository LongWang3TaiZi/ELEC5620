<template>
  <div class="container">
    <!-- Financial Product Recommendations -->
    <el-table :data="products">
      <el-table-column prop="id" label="ID"></el-table-column>
      <el-table-column
        prop="productName"
        label="Product Name"
      ></el-table-column>
      <el-table-column
        prop="supplierName"
        label="Supplier Name"
      ></el-table-column>
      <el-table-column
        prop="productInfo"
        label="Product Info"
      ></el-table-column>
      <el-table-column label="Actions">
        <template #default="scope">
          <el-button
            type="primary"
            @click="addToFavorites(scope.row)"
            :disabled="scope.row.isCollect == true"
          >
            Bookmark
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
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
import { getCurrentInstance, ref } from 'vue'
import { ElTable, ElTableColumn, ElButton, ElPagination } from 'element-plus'
import { fetchGetRecommendList, fetchGetRecommendCollect } from '@/api/user'
import { getItem } from '@/utils/storage'

// Product data
const products = ref([])
const queryParams = ref({
  pageSize: 10,
  pageNum: 1,
})

// Favorites list
const favorites = ref([])
const { proxy: ctx } = getCurrentInstance()

// Add product to favorites
const addToFavorites = product => {
  favorites.value.push(product)
  let customerId = getItem('userInfo').id
  let data = {
    productId: product.id,
  }
  fetchGetRecommendCollect(customerId, data).then(res => {
    getPageList()
    ctx.$message.success({
      message: 'Bookmark Success',
      duration: 1000,
    })
  })
}

// Fetch product list
getPageList()
function getPageList() {
  fetchGetRecommendList(getItem('userInfo').id, queryParams.value).then(res => {
    products.value = res
  })
}
</script>

<style scoped>
.container {
  padding: 20px;
}

.product-name {
  font-size: 18px;
  font-weight: bold;
}

.product-details,
.product-contact {
  margin-top: 5px;
}
</style>
