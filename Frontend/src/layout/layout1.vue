<template>
  <div class="wrapper" :class="{ fluid: isFluid }">
    <!-- 固定侧边栏内容，仅包含 /chat 和 /manageUsers -->
    <div class="sidebar">
      <el-menu default-active="chat" router>
        <el-menu-item index="/chat">
          <router-link to="/chat">Chat</router-link>
        </el-menu-item>
        <el-menu-item index="/manageUsers">
          <router-link to="/manageUsers">Manage Users</router-link>
        </el-menu-item>
      </el-menu>
    </div>

    <div class="right" :class="{ flex: isTopbarFixed }">
      <div class="top">
        <topbar />
        <tagsbar />
        <breadcrumbs
          v-if="isBreadcrumbsShow"
          @on-breadcrumbs-change="handleBreadcrumbsChange"
        />
      </div>
      <div class="main" :class="{ pt0: isBreadcrumbsShow && paddingFlag }">
        <Content />
      </div>
    </div>
  </div>
</template>

<script>
import { defineComponent, ref, computed } from 'vue'
import Topbar from './components/Topbar/index.vue'
import Tagsbar from './components/Tagsbar/index.vue'
import Breadcrumbs from './components/Topbar/Breadcrumbs.vue'
import Content from './components/Content/index.vue'
import { useResizeHandler } from './hooks/useResizeHandler'
import { useLayoutsettings } from '@/pinia/modules/layoutSettings'

export default defineComponent({
  name: 'layout1',
  components: {
    Topbar,
    Tagsbar,
    Breadcrumbs,
    Content,
  },
  setup() {
    useResizeHandler()
    const defaultSettings = useLayoutsettings()
    const isFluid = defaultSettings.layout.isFluid
    const isTopbarFixed = defaultSettings.topbar.isFixed
    const isBreadcrumbsShow = computed(() => defaultSettings.breadcrumbs.isShow)
    const paddingFlag = ref(true)

    const handleBreadcrumbsChange = boo => {
      paddingFlag.value = boo
    }

    return {
      isFluid,
      isTopbarFixed,
      isBreadcrumbsShow,
      paddingFlag,
      handleBreadcrumbsChange,
    }
  },
})
</script>

<style lang="scss" scoped>
.wrapper {
  display: flex;
  margin: 0 auto;
  width: 1440px;
  height: 100%;
  &.fluid {
    width: 100%;
  }

  .sidebar {
    width: 240px;
    background-color: #333;
    color: #fff;
    .el-menu-item {
      color: #fff;
    }
  }

  .right {
    flex: 1;
    overflow: auto;
    &.flex {
      overflow: hidden;
      display: flex;
      flex-direction: column;
    }
    .top {
      background: #fff;
    }
    .main {
      flex: 1;
      background: #f5f5f5;
      padding: 16px;
      overflow: auto;
      &.pt0 {
        padding-top: 0;
      }
    }
  }
}
</style>
