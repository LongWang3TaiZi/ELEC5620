<template>
  <div class="container">
    <!-- User Input Form -->
    <el-form
      :model="financialDetail"
      label-width="120px"
      class="form-container"
    >
      <el-form-item label="Savings">
        <el-input
          v-model="financialDetail.savings"
          placeholder="Enter savings"
        />
      </el-form-item>
      <el-form-item label="Debts">
        <el-input v-model="financialDetail.debts" placeholder="Enter debts" />
      </el-form-item>
      <el-form-item label="Salary">
        <el-input v-model="financialDetail.salary" placeholder="Enter salary" />
      </el-form-item>
      <el-form-item label="Fixed Assets">
        <el-input
          v-model="financialDetail.fixedAssets"
          placeholder="Enter fixed assets"
        />
      </el-form-item>
    </el-form>

    <!-- Generate Report Button -->
    <div class="button-container">
      <el-button type="primary" size="large" @click="generateReport">
        Generate Report
      </el-button>
    </div>

    <!-- Display Analysis Result -->
    <div v-if="report" class="report-container">
      <p><strong>Analysis Report:</strong></p>
      <p class="analysis-details">{{ report.analysisDetails }}</p>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { ElButton, ElForm, ElFormItem, ElInput } from 'element-plus'
import { getItem } from '@/utils/storage'

// 用户输入的财务详细信息
const financialDetail = ref({
  savings: null,
  debts: null,
  salary: null,
  fixedAssets: null,
})

// 分析结果
const report = ref(null)

// 生成新的资产分析报告
async function generateReport() {
  const customerId = getItem('userInfo').id

  try {
    const response = await fetch(
      `http://localhost:8081/api/customer/analyzeAssets/${customerId}`,
      {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          deposit: financialDetail.value.savings,
          liability: financialDetail.value.debts,
          income: financialDetail.value.salary,
          fixedAssets: financialDetail.value.fixedAssets,
        }),
      }
    )

    if (!response.ok) {
      throw new Error('Failed to generate report')
    }

    const res = await response.json()
    console.log('API Response:', res) // 检查返回的数据

    if (res.analysisDetails) {
      // 直接设置分析报告详情
      report.value = { analysisDetails: res.analysisDetails }
      // 重置输入字段
      financialDetail.value = {
        savings: null,
        debts: null,
        salary: null,
        fixedAssets: null,
      }
    } else {
      console.error('Error:', res.message || 'Unknown error')
    }
  } catch (error) {
    console.error('Request failed:', error)
  }
}
</script>

<style scoped>
.container {
  padding: 20px;
  background-color: #f8f9fa;
  border-radius: 8px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.form-container {
  margin-bottom: 20px;
}

.button-container {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

.report-container {
  margin-top: 20px;
  background-color: #ffffff;
  padding: 15px;
  border-radius: 8px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.analysis-details {
  margin: 10px 0;
  font-style: italic;
}
</style>
