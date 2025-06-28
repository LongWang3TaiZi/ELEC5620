<template>
  <div class="user-center">
    <el-row justify="center">
      <el-col :span="12">
        <el-card>
          <h1 class="title">User Center</h1>
          <el-form
            :model="user"
            ref="userForm"
            label-width="120px"
            class="form"
          >
            <!-- 展示 Username -->
            <el-form-item label="Username" prop="username">
              <el-input
                v-model="user.username"
                :disabled="!isEditing"
              ></el-input>
            </el-form-item>

            <!-- 展示 Role -->
            <el-form-item label="Role" prop="role">
              <el-input v-model="user.role" disabled></el-input>
              <!-- Role 不允许修改 -->
            </el-form-item>

            <!-- 展示 Email -->
            <el-form-item label="Email" prop="email">
              <el-input v-model="user.email" :disabled="!isEditing"></el-input>
            </el-form-item>

            <!-- 展示 Password -->
            <el-form-item label="Password" prop="password">
              <el-input
                v-model="user.password"
                type="password"
                :disabled="!isEditing"
              ></el-input>
            </el-form-item>

            <!-- 编辑/保存/取消按钮 -->
            <el-form-item>
              <div v-if="!isEditing" class="button-center">
                <el-button type="primary" @click="editUserInfo">
                  Modify
                </el-button>
              </div>
              <div v-else class="button-center">
                <el-button type="primary" @click="saveUserInfo">Save</el-button>
                <el-button @click="cancelEdit">Cancel</el-button>
              </div>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { ref } from 'vue'
import { useAccount } from '@/pinia/modules/account'

export default {
  setup() {
    const { userinfo, getUserinfo } = useAccount()

    // 初始化用户数据，包括 username, role 和 email
    const originalUser = ref({
      username: userinfo.username,
      role: userinfo.role || 'user', // 默认 role 为 'user'
      email: userinfo.email || 'example@domain.com',
      password: '******', // 显示初始密码，假设为不可编辑字段
    })

    const user = ref({ ...originalUser.value }) // 拷贝一个用户对象，允许修改
    const isEditing = ref(false) // 是否处于编辑模式

    // 进入编辑模式
    const editUserInfo = () => {
      isEditing.value = true
    }

    // 保存修改4
    const saveUserInfo = () => {
      console.log('User information saved:', user.value)
      originalUser.value = { ...user.value } // 更新原始数据
      isEditing.value = false // 退出编辑模式
    }

    // 取消编辑并恢复原始数据
    const cancelEdit = () => {
      user.value = { ...originalUser.value } // 重置为原始数据
      isEditing.value = false // 退出编辑模式
    }

    return {
      user,
      isEditing,
      editUserInfo,
      saveUserInfo,
      cancelEdit,
    }
  },
}
</script>

<style scoped>
.user-center {
  padding: 40px;
  background-color: #f5f5f5;
  min-height: 100vh;
}

.title {
  text-align: center;
  font-size: 24px;
  margin-bottom: 20px;
  color: #333;
}

.el-card {
  padding: 20px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.form {
  max-width: 500px;
  margin: 0 auto;
}

.button-center {
  text-align: center;
}
</style>
