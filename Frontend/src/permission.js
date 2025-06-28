import { ElLoading } from 'element-plus'
import router, { asyncRoutes } from '@/router'
// import { TOKEN } from './pinia/modules/app' // 移除 TOKEN
import { nextTick } from 'vue'
import { useApp } from './pinia/modules/app'
import { useAccount } from './pinia/modules/account'
import { useMenus } from './pinia/modules/menu'
// import { getItem } from './utils/storage' // 移除相关方法

const getPageTitle = title => {
  const { title: appTitle } = useApp()
  if (title) {
    return `${title} - ${appTitle}`
  }
  return appTitle
}

// 白名单，里面是路由对象的name
const WhiteList = ['login', 'lock', 'register']

let loadingInstance = null

// vue-router4的路由守卫不再是通过next放行，而是通过return返回true或false或者一个路由地址
router.beforeEach(async to => {
  loadingInstance = ElLoading.service({
    lock: true,
    // text: '正在加载数据，请稍候~',
    background: 'rgba(0, 0, 0, 0.7)',
  })

  // 白名单页面允许直接访问
  if (WhiteList.includes(to.name)) {
    return true
  }

  // 验证用户是否已登录
  const userId = localStorage.getItem('id')
  console.log('User ID:', userId)

  if (!userId) {
    loadingInstance.close()
    return { name: 'login' } // 未登录重定向到登录页面
  }

  // 生成菜单（如果你的项目有动态菜单，在此处会添加动态路由）
  const { menus, generateMenus } = useMenus()
  if (menus.length <= 0) {
    try {
      await generateMenus() // 调用菜单生成方法并添加动态路由
      return to.fullPath // 继续导航到目标路径
    } catch (err) {
      loadingInstance.close()
      return false // 生成菜单失败时终止导航
    }
  }

  // 判断是否处于锁屏状态
  if (to.name !== 'lock') {
    const { authorization } = useApp()
    if (!!authorization && !!authorization.screenCode) {
      return {
        name: 'lock',
        query: {
          redirect: to.path,
        },
        replace: true,
      }
    }
  }
})

router.beforeEach((to, from, next) => {
  if (to.fullPath.includes('/error') != -1) {
    // to.redirectedFrom
    // router.push('/recommend')
  }
  next() // 无条件放行所有页面
})

router.afterEach(to => {
  loadingInstance.close()
  if (router.currentRoute.value.name === to.name) {
    nextTick(() => {
      document.title = getPageTitle(!!to.meta && to.meta.truetitle)
    })
  }
})
