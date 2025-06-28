/*
 * @Descripttion:
 * @version:
 * @Date: 2021-04-20 11:06:21
 * @LastEditors: huzhushan@126.com
 * @LastEditTime: 2022-09-24 19:27:21
 * @Author: huzhushan@126.com
 * @HomePage: https://huzhushan.gitee.io/vue3-element-admin
 * @Github: https://github.com/huzhushan/vue3-element-admin
 * @Donate: https://huzhushan.gitee.io/vue3-element-admin/donate/
 */
// home.js
const Layout = () => import('@/layout/index.vue')
const Recommend = () => import('@/views/user/recommend.vue')
const Report = () => import('@/views/user/report.vue')
const Chat = () => import('@/components/Chat/index.vue')
export default [
  {
    path: '/user',
    component: Layout,
    redirect: '/chat',
    meta: {
      title: 'menu.dashboard',
    },
    icon: 'icon-home',
    children: [
      {
        path: '/recommend',
        name: 'Recommend',
        component: Recommend,
        meta: {
          title: 'Recommend',
          affix: false,
        },
      },
      {
        path: '/report',
        name: 'Report',
        component: Report,
        meta: {
          title: 'Report',
          affix: false,
        },
      },
      {
        path: '/chat',
        name: 'chat',
        component: Chat,
        meta: {
          title: 'Chat',
          affix: true,
        },
      },
    ],
  },
]
