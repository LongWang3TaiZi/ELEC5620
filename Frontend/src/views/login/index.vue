<template>
  <div class="login">
    <el-form class="form" :model="model" :rules="rules" ref="loginForm">
      <h1 class="title">Financial Assistant</h1>

      <!-- 邮箱输入框 -->
      <el-form-item prop="email">
        <el-input
          class="text"
          v-model="model.email"
          prefix-icon="User"
          clearable
          :placeholder="$t('login.email')"
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
          :placeholder="$t('login.password')"
        />
      </el-form-item>

      <!-- 登录按钮 -->
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

      <!-- 新增：Sign Up 按钮 -->
      <el-form-item>
        <p class="signup-redirect">
          Don't have an account?
          <el-button type="text" @click="redirectToRegister">
            Sign Up
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
import { Login } from '@/api/login' // 假设存在登录API
import { useRouter } from 'vue-router'
import ChangeLang from '@/layout/components/Topbar/ChangeLang.vue'
import { setItem } from '@/utils/storage'

export default defineComponent({
  components: { ChangeLang },
  name: 'login',
  setup() {
    const { proxy: ctx } = getCurrentInstance()
    const router = useRouter()

    // 验证规则
    const getRules = () => ({
      email: [
        {
          required: true,
          message: ctx.$t('login.rules-email'),
          trigger: 'blur',
        },
        {
          type: 'email',
          message: ctx.$t('login.rules-valid-email'),
          trigger: 'blur',
        },
      ],
      password: [
        {
          required: true,
          message: ctx.$t('login.rules-password'),
          trigger: 'blur',
        },
        {
          min: 6,
          max: 12,
          message: ctx.$t('login.rules-regpassword'),
          trigger: 'blur',
        },
      ],
    })

    const state = reactive({
      model: {
        email: '',
        password: '',
      },
      rules: getRules(),
      loading: false,
      btnText: computed(() =>
        state.loading ? ctx.$t('login.logining') : ctx.$t('login.login')
      ),
      loginForm: ref(null),
    })

    // 登录函数
    const submit = () => {
      if (state.loading) return
      state.loginForm.validate(async valid => {
        if (valid) {
          state.loading = true
          try {
            // 调用登录 API 并获取响应数据
            const userData = await Login(state.model)
            const { responseCode, user } = userData
            console.log(user, 'user')
            if (responseCode === 'A1') {
              ctx.$message.success({
                message: ctx.$t('login.loginsuccess'),
                duration: 1000,
              })
              setItem('userInfo', user)
              // console.log(user, "qwe");
              if (user.role === 'customer') {
                router.push('/user')
              } else if (user.role === 'bank') {
                console.log('11111111')
                router.push('/bank')
              } else if (user.role === 'admin') {
                router.push('/manage-users')
                //router.push('/admin')
              } else {
                router.push('/home')
              }
            } else {
              ctx.$message.error(userData.message || '登录失败')
            }
          } catch (error) {
            ctx.$message.error(error.message || '登录失败，请稍后重试')
          } finally {
            state.loading = false
          }
        }
      })
    }

    // 跳转到注册页面
    const redirectToRegister = () => {
      router.push('/register')
    }

    return {
      ...toRefs(state),
      submit,
      redirectToRegister,
    }
  },
})
</script>

<style lang="scss" scoped>
.login {
  transition: transform 1s;
  transform: scale(1);
  width: 100%;
  height: 100%;
  overflow: hidden;
  background: #2d3a4b;
  .form {
    width: 520px;
    max-width: 100%;
    padding: 0 24px;
    box-sizing: border-box;
    margin: 160px auto 0;
    :deep {
      .el-input__wrapper {
        box-shadow: 0 0 0 1px rgba(255, 255, 255, 0.1) inset;
        background: rgba(0, 0, 0, 0.1);
      }
    }
    .title {
      color: #fff;
      text-align: center;
      font-size: 24px;
      margin: 0 0 24px;
    }
    .btn {
      width: 100%;
    }
    .signup-redirect {
      text-align: center;
      margin-top: 20px;
      font-size: 14px;
      color: #fff;
    }
  }
}
</style>
