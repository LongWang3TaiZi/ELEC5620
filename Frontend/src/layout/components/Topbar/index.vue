<template>
  <div class="header" :class="{ 'no-border': isHorizontalMenu }">
    <div class="navigation">
      <logo
        v-if="isShowLogo"
        class="mobile"
        :class="{ 'show-title': isHorizontalMenu }"
      />
      <hamburger v-if="isShowHamburger" />
      <breadcrumbs v-if="isShowBreadcrumbs" />
    </div>
    <div class="action">
      <error-log />
      <userinfo />
      <change-lang />
      <!-- Logout Button -->
      <button @click="showLogoutDialog = true" class="logout-button">
        Logout
      </button>
      <!-- Logout Confirmation Dialog -->
      <div v-if="showLogoutDialog" class="logout-dialog">
        <p class="logout-message">Are you sure you want to logout?</p>
        <div class="logout-buttons">
          <button @click="logout" class="confirm-button">Confirm</button>
          <button @click="cancelLogout" class="cancel-button">Cancel</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { defineComponent, computed, ref } from 'vue'
import Logo from '@/layout/components/Sidebar/Logo.vue'
import Hamburger from './Hamburger.vue'
import Breadcrumbs from './Breadcrumbs.vue'
import Userinfo from './Userinfo.vue'
import ChangeLang from './ChangeLang.vue'
import ErrorLog from '@/components/ErrorLog/index.vue'
import { useLayoutsettings } from '@/pinia/modules/layoutSettings'
import { storeToRefs } from 'pinia'
import { useApp } from '@/pinia/modules/app'

export default defineComponent({
  components: {
    Hamburger,
    Breadcrumbs,
  },
  setup() {
    const defaultSettings = useLayoutsettings()
    const { device } = storeToRefs(useApp())

    const isHorizontalMenu = computed(
      () => defaultSettings.menus.mode === 'horizontal'
    )
    const isShowLogo = computed(
      () => isHorizontalMenu.value || device.value === 'mobile'
    )
    const isShowHamburger = computed(() => !isHorizontalMenu.value)
    const isShowBreadcrumbs = computed(
      () => defaultSettings.breadcrumbs.isShow && !isHorizontalMenu.value
    )

    const showLogoutDialog = ref(false)

    const logout = () => {
      localStorage.clear()
      showLogoutDialog.value = false
      window.location.href = '/login'
    }

    const cancelLogout = () => {
      showLogoutDialog.value = false
    }

    return {
      device,
      isHorizontalMenu,
      isShowLogo,
      isShowHamburger,
      isShowBreadcrumbs,
      showLogoutDialog,
      logout,
      cancelLogout,
    }
  },
})
</script>

<style lang="scss" scoped>
.header {
  height: 48px;
  border-bottom: 1px solid #e0e4ef;
  display: flex;
  justify-content: space-between;
  &.no-border {
    border: none;
  }
  .navigation {
    display: flex;
    align-items: center;
    overflow: hidden;
  }
  .action {
    display: flex;
    align-items: center;
    position: relative;
  }
}

.logout-button {
  margin-left: 10px;
  padding: 6px 12px;
  cursor: pointer;
  background-color: #ff4d4f;
  color: white;
  border: none;
  border-radius: 5px;
  transition: background-color 0.3s;
}

.logout-button:hover {
  background-color: #e03e3f;
}

.logout-dialog {
  position: absolute;
  top: 60px;
  right: 10px;
  background-color: #fff;
  border-radius: 8px;
  padding: 20px;
  width: 250px;
  box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.15);
  z-index: 10;
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
}

.logout-message {
  font-size: 16px;
  color: #333;
  margin-bottom: 16px;
}

.logout-buttons {
  display: flex;
  gap: 12px;
}

.confirm-button {
  background-color: #ff4d4f;
  color: white;
  border: none;
  padding: 8px 16px;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.confirm-button:hover {
  background-color: #e03e3f;
}

.cancel-button {
  background-color: #ccc;
  color: white;
  border: none;
  padding: 8px 16px;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.cancel-button:hover {
  background-color: #bbb;
}
</style>
