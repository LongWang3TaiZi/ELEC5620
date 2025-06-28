<template>
  <div class="register-container">
    <el-form class="form" :model="model" :rules="rules" ref="registerForm">
      <h1 class="title">Sign up</h1>

      <!-- 用户名输入框 -->
      <el-form-item prop="username">
        <el-input
          class="text"
          v-model="model.username"
          prefix-icon="User"
          clearable
          :placeholder="$t('username')"
        />
      </el-form-item>

      <!-- 邮箱输入框 -->
      <el-form-item prop="email">
        <el-input
          class="text"
          v-model="model.email"
          prefix-icon="Mail"
          clearable
          :placeholder="$t('email')"
        />
      </el-form-item>

      <!-- 密码输入框 -->
      <el-form-item prop="password">
        <el-input
          class="text"
          v-model="model.password"
          prefix-icon="Lock"
          show-password
          clearable
          :placeholder="$t('password')"
        />
      </el-form-item>

      <!-- 确认密码输入框 -->
      <el-form-item prop="confirmPassword">
        <el-input
          class="text"
          v-model="model.confirmPassword"
          prefix-icon="Lock"
          show-password
          clearable
          :placeholder="$t('confirmPassword')"
        />
      </el-form-item>

      <!-- 角色选择框 -->
      <el-form-item prop="role">
        <el-select v-model="model.role" placeholder="Select a role">
          <el-option label="Bank" value="bank"></el-option>
          <el-option label="Customer" value="customer"></el-option>
          <el-option label="Admin" value="admin"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button
          :loading="loading"
          type="primary"
          class="btn"
          size="large"
          @click="submit"
        >
          {{ btnText }}
        </el-button>
      </el-form-item>

      <!-- 已有账号？跳转到登录按钮 -->
      <el-form-item>
        <p class="login-redirect">
          Already have an account?
          <el-button type="text" @click="redirectToLogin">
            Log In
          </el-button>
        </p>
      </el-form-item>
    </el-form>
  </div>
  <div class="change-lang">
    <change-lang />
  </div>
</template>

<script>
import {
  defineComponent,
  reactive,
  toRefs,
  ref,
  computed,
  getCurrentInstance,
} from 'vue'
import { useRouter } from 'vue-router'
import ChangeLang from '@/layout/components/Topbar/ChangeLang.vue'

export default defineComponent({
  components: { ChangeLang },
  name: 'register',
  setup() {
    const { proxy: ctx } = getCurrentInstance()
    const router = useRouter()

    const state = reactive({
      model: {
        username: '',
        email: '',
        password: '',
        confirmPassword: '',
        role: '',
      },
      rules: {
        username: [
          {
            required: true,
            message: ctx.$t('register.rules-username'),
            trigger: 'blur',
          },
        ],
        email: [
          {
            required: true,
            type: 'email',
            message: ctx.$t('register.rules-email'),
            trigger: 'blur',
          },
        ],
        password: [
          {
            required: true,
            message: ctx.$t('register.rules-password'),
            trigger: 'blur',
          },
          {
            min: 6,
            max: 12,
            message: ctx.$t('register.rules-regpassword'),
            trigger: 'blur',
          },
        ],
        confirmPassword: [
          {
            required: true,
            message: ctx.$t('register.rules-confirmPassword'),
            trigger: 'blur',
          },
          {
            validator: (rule, value, callback) => {
              if (value !== state.model.password) {
                callback(new Error('Password confirmation does not match.'))
              } else {
                callback()
              }
            },
            trigger: 'blur',
          },
        ],
        role: [
          {
            required: true,
            message: 'Please select a role',
            trigger: 'change',
          },
        ],
      },
      loading: false,
      btnText: computed(() =>
        state.loading
          ? ctx.$t('register.registering')
          : ctx.$t('register.register')
      ),
      registerForm: ref(null),
    })

    const submit = async () => {
      if (state.loading) return
      state.registerForm.validate(async valid => {
        if (valid) {
          state.loading = true
          try {
            const response = await fetch(
              'http://localhost:8081/api/user/register',
              {
                method: 'POST',
                headers: {
                  'Content-Type': 'application/json',
                },
                body: JSON.stringify(state.model),
              }
            )
            if (!response.ok) {
              throw new Error(
                'Username already exists. Please try a different one.'
              )
            }
            const result = await response.json()
            if (result.responseCode === 'A0') {
              ctx.$message.success(ctx.$t('register.success'))
              router.push('/login')
            } else {
              ctx.$message.error(result.message)
            }
          } catch (error) {
            ctx.$message.error(error.message || 'Failed to register')
          } finally {
            state.loading = false
          }
        }
      })
    }

    const redirectToLogin = () => {
      router.push('/login')
    }

    return {
      ...toRefs(state),
      submit,
      redirectToLogin,
    }
  },
})
</script>

<style lang="scss" scoped>
.register-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: #2c2c2c;
  padding: 20px;
}

.form {
  width: 100%;
  max-width: 400px;
  padding: 30px;
  border-radius: 8px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.5);
  background: #1e1e1e;
}

.title {
  text-align: center;
  font-size: 24px;
  font-weight: bold;
  color: #ffffff;
  margin-bottom: 20px;
}

.el-form-item {
  margin-bottom: 16px;
}

.text {
  border-radius: 6px;
  font-size: 16px;
  background-color: #333333;
  color: #ffffff;
}

.el-input__inner,
.el-select .el-input__inner {
  border-radius: 6px;
  padding: 12px;
  background-color: #333333;
  color: #ffffff;
  border: none;
}

.btn {
  width: 100%;
  font-size: 16px;
  border-radius: 6px;
  background-color: #409eff;
  color: white;
  transition: background-color 0.3s ease;
}

.btn:hover {
  background-color: #66b1ff;
}

.login-redirect {
  text-align: center;
  font-size: 14px;
  color: #bbbbbb;
}

.login-redirect el-button {
  color: #409eff;
  font-weight: bold;
}
</style>
