<template>
  <el-dropdown trigger="hover">
    <div class="userinfo">
      <template v-if="!userinfo">
        <i class="el-icon-user" />
        admin
      </template>
      <template v-else>
        <img class="avatar" :src="userinfo.avatar" />
        {{ userinfo.name }}
      </template>
    </div>
    <template #dropdown>
      <el-dropdown-menu>
        <el-dropdown-item @click="goToUserCenter">
          {{ $t('topbar.center') }}
        </el-dropdown-item>
        <el-dropdown-item>{{ $t('topbar.password') }}</el-dropdown-item>
        <lock-modal />
        <el-dropdown-item @click="logout">
          {{ $t('topbar.logout') }}
        </el-dropdown-item>
      </el-dropdown-menu>
    </template>
  </el-dropdown>
</template>

<script>
import { defineComponent } from 'vue'
import { useRouter } from 'vue-router'
import { useUserinfo } from '@/components/Avatar/hooks/useUserinfo'
import LockModal from './LockModal.vue'
import { useApp } from '@/pinia/modules/app'
import { removeItem } from '@/utils/storage'

export default defineComponent({
  components: {
    LockModal,
  },
  setup() {
    const router = useRouter()
    const { userinfo } = useUserinfo()

    const goToUserCenter = () => {
      router.push('/user-center') // 跳转到用户中心页面
    }

    // 退出
    const logout = () => {
      useApp().clearToken()
      removeItem('userInfo')
      router.push('/login?redirect=/')
    }

    return {
      userinfo,
      goToUserCenter,
      logout,
    }
  },
})
</script>
