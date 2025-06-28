<template>
  <div class="manage-page">
    <h2>User Management</h2>

    <!-- Logout Button -->
    <div class="logout-section">
      <button @click="showLogoutDialog = true" class="logout-button">
        Logout
      </button>
    </div>

    <!-- Logout Confirmation Dialog -->
    <el-dialog
      v-model="showLogoutDialog"
      title="Logout Confirmation"
      width="300px"
      :close-on-click-modal="false"
    >
      <span>Are you sure you want to logout?</span>
      <template #footer>
        <el-button @click="showLogoutDialog = false">Cancel</el-button>
        <el-button type="primary" @click="confirmLogout">Confirm</el-button>
      </template>
    </el-dialog>

    <!-- User Table -->
    <el-table :data="users" v-loading="loading" style="width: 100%">
      <!-- Username column -->
      <el-table-column label="Username">
        <template #default="scope">
          <div v-if="scope.row.isEditing">
            <el-input v-model="scope.row.username" size="small" />
          </div>
          <div v-else>{{ scope.row.username }}</div>
        </template>
      </el-table-column>

      <!-- Role column -->
      <el-table-column label="Role">
        <template #default="scope">
          <div v-if="scope.row.isEditing">
            <el-select
              v-model="scope.row.role"
              placeholder="Select role"
              size="small"
            >
              <el-option label="Admin" value="admin"></el-option>
              <el-option label="Bank" value="bank"></el-option>
              <el-option label="Customer" value="customer"></el-option>
            </el-select>
          </div>
          <div v-else>{{ scope.row.role }}</div>
        </template>
      </el-table-column>

      <!-- Email column -->
      <el-table-column label="Email">
        <template #default="scope">
          <div>{{ scope.row.email }}</div>
        </template>
      </el-table-column>

      <!-- Actions column -->
      <el-table-column label="Actions" width="250">
        <template #default="scope">
          <el-button
            v-if="!scope.row.isEditing"
            @click="enableEditing(scope.row)"
            type="primary"
            size="small"
          >
            Edit
          </el-button>
          <div v-else>
            <el-button
              @click="saveChanges(scope.row)"
              type="success"
              size="small"
            >
              Save
            </el-button>
            <el-button
              @click="cancelChanges(scope.row)"
              type="danger"
              size="small"
            >
              Cancel
            </el-button>
          </div>

          <!-- Delete confirmation using el-popconfirm -->
          <el-popconfirm
            title="Are you sure you want to delete this user?"
            @confirm="deleteUser(scope.row)"
            @cancel="cancelDelete"
          >
            <template #reference>
              <el-button type="danger" size="small">Delete</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <!-- Pagination component -->
    <el-pagination
      background
      layout="prev, pager, next"
      :total="total"
      :page-size="20"
      @current-change="handlePageChange"
    ></el-pagination>

    <!-- Chat Section -->
    <div class="chat-section">
      <h3>Chat</h3>
      <div class="chatbot">
        <div class="chatbox-messages">
          <div
            v-for="(msg, index) in messages"
            :key="index"
            :class="`chat-message ${msg.sender}`"
          >
            {{ msg.text }}
          </div>
          <div v-if="isLoading" class="chat-message bot">Typing...</div>
        </div>
        <div class="chatbox-input">
          <input
            type="text"
            v-model="userInput"
            @input="handleInputChange"
            placeholder="You can ask questions here"
          />
          <button :disabled="isLoading" @click="handleSendMessage">
            {{ isLoading ? 'Sending...' : 'Send' }}
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import axios from 'axios'

export default {
  name: 'ManageUsers',
  setup() {
    const users = ref([])
    const total = ref(0)
    const loading = ref(false)
    const showLogoutDialog = ref(false)
    const messages = ref([])
    const userInput = ref('')
    const isLoading = ref(false)

    const fetchUsers = async (page = 1) => {
      loading.value = true
      try {
        const response = await fetch(
          `http://localhost:8081/api/users?page=${page}&size=20`
        )
        const data = await response.json()
        users.value = data.map(user => ({
          ...user,
          isEditing: false, // 添加isEditing属性
        }))
        total.value = data.total || response.headers.get('x-total-count')
      } catch (error) {
        console.error('Error fetching users:', error)
      } finally {
        loading.value = false
      }
    }

    const confirmLogout = () => {
      localStorage.clear()
      showLogoutDialog.value = false
      window.location.href = '/login'
    }

    const enableEditing = user => {
      // 启用编辑模式
      user.isEditing = true
    }

    const cancelChanges = user => {
      // 取消编辑模式并重置为原始值
      user.isEditing = false
      fetchUsers() // 重新获取用户数据以取消更改
    }

    const saveChanges = async user => {
      try {
        const response = await fetch(
          `http://localhost:8081/api/users/${user.id}`,
          {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username: user.username, role: user.role }),
          }
        )
        const data = await response.json()
        if (data.responseCode === 'A2') {
          user.isEditing = false
          ElMessage.success(data.message)
        } else {
          ElMessage.error(data.message)
        }
      } catch (error) {
        console.error('Error updating user:', error)
        ElMessage.error('Update failed')
      }
    }

    onMounted(() => {
      fetchUsers()
    })

    return {
      users,
      total,
      loading,
      showLogoutDialog,
      confirmLogout,
      enableEditing,
      saveChanges,
      cancelChanges,
      messages,
      userInput,
      isLoading,
    }
  },
}
</script>

<style scoped>
.manage-page {
  padding: 20px;
  font-family: Arial, sans-serif;
  background-color: #f4f6f8;
}

h2 {
  color: #34495e;
  font-weight: 600;
}

.logout-section {
  text-align: right;
  margin-bottom: 20px;
}

.logout-button {
  padding: 8px 16px;
  cursor: pointer;
  background-color: #ff6b6b;
  color: white;
  border: none;
  border-radius: 6px;
  transition: background-color 0.3s ease;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
}

.logout-button:hover {
  background-color: #ff3b3b;
}

.el-dialog {
  border-radius: 8px;
}

.el-button {
  border-radius: 6px;
}

.el-table {
  background-color: #ffffff;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

.el-table th {
  background-color: #ecf0f1;
  color: #34495e;
  font-weight: bold;
}

.el-table td {
  color: #555555;
}

.chat-section {
  margin-top: 40px;
  background-color: #ffffff;
  border: 1px solid #e0e0e0;
  border-radius: 8px;
  padding: 20px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

.chat-section h3 {
  color: #34495e;
}

.chatbot {
  display: flex;
  flex-direction: column;
  height: 300px;
  background-color: #f9f9f9;
  border-radius: 8px;
}

.chatbox-messages {
  flex-grow: 1;
  padding: 15px;
  overflow-y: auto;
}

.chat-message {
  margin-bottom: 10px;
  padding: 10px 15px;
  border-radius: 16px;
  max-width: 70%;
}

.chat-message.user {
  background-color: #d1f2eb;
  align-self: flex-end;
  color: #2c3e50;
}

.chat-message.bot {
  background-color: #eaf2f8;
  align-self: flex-start;
  color: #2c3e50;
}

.chatbox-input {
  display: flex;
  padding: 10px;
  border-top: 1px solid #e0e0e0;
}

.chatbox-input input {
  flex-grow: 1;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 16px;
  outline: none;
  transition: border-color 0.3s ease;
}

.chatbox-input input:focus {
  border-color: #007bff;
}

.chatbox-input button {
  margin-left: 10px;
  padding: 10px 16px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 16px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
}

.chatbox-input button:hover {
  background-color: #0056b3;
}

.chatbox-input button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}
</style>
