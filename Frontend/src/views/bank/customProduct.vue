<template>
  <div class="container">
    <el-button @click="editProduct" style="margin-bottom:10px;">
      Add New
    </el-button>
    <!-- Product List -->
    <el-table :data="products" style="width: 100%">
      <el-table-column prop="productName" label="Product Name" />
      <el-table-column prop="productInfo" label="Product Info" />
      <el-table-column prop="supplierName" label="Supplier Name" />
      <el-table-column label="Actions">
        <template #default="{ row }">
          <el-button type="primary" @click="editProduct(row)">Edit</el-button>
          <el-button type="danger" @click="deleteProduct(row.id)">
            Delete
          </el-button>
        </template>
      </el-table-column>
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
    <el-dialog v-model="dialogVisible" title="Add New Product" width="500">
      <!-- Add Product Form -->
      <el-form label-width="120px">
        <el-form-item label="Product Name">
          <el-input v-model="newProduct.productName" />
        </el-form-item>
        <el-form-item label="Supplier Name">
          <el-input v-model="newProduct.supplierName" />
        </el-form-item>
        <el-form-item label="Product Info">
          <el-input v-model="newProduct.productInfo" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="addProduct">Confirm</el-button>
          <el-button @click="cancelAdd">Cancel</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import {
  fetchCustomProductList,
  fetchAddCustomProduct,
  fetchUpdateCustomProduct,
  fetchDelCustomProduct,
} from '@/api/bank'
import { getItem } from '@/utils/storage'

// Product list
const products = ref([])
const total = ref(0)
// New product object
const newProduct = ref({
  productInfo: '',
  supplierName: '',
  productName: '',
  bankId: '',
})
const queryParams = ref({
  pageSize: 10,
  pageNum: 1,
})

// Fetch data
getPageList()
function getPageList() {
  fetchCustomProductList(queryParams.value).then(res => {
    products.value = res
  })
}

// Control dialog visibility
const dialogVisible = ref(false)

// Add product
const addProduct = async () => {
  console.log(newProduct.value, 'newProduct.value')
  newProduct.value.bankId = getItem('userInfo').id
  const res = newProduct.value.id
    ? await fetchUpdateCustomProduct(newProduct.value.id, newProduct.value)
    : await fetchAddCustomProduct(newProduct.value)
  if (res) {
    getPageList()
  }
  newProduct.value = {
    productInfo: '',
    supplierName: '',
    productName: '',
    id: '',
  }
  dialogVisible.value = false
}

// Cancel add
const cancelAdd = () => {
  newProduct.value = {
    productInfo: '',
    supplierName: '',
    productName: '',
    id: '',
  }
  dialogVisible.value = false
}

// Edit product
const editProduct = product => {
  newProduct.value = { ...product }
  dialogVisible.value = true
}

// Delete product
const deleteProduct = id => {
  fetchDelCustomProduct(id).then(res => {
    getPageList()
  })
}
</script>

<style scoped>
.container {
  padding: 20px;
}
</style>
