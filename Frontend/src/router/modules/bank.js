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
const CustomProduct = () => import('@/views/bank/customProduct.vue')
const CustomList = () => import('@/views/bank/customList.vue')
const Chat = () => import('@/components/Chat/index.vue')
const ManageUsers = () => import('@/views/admin/manageUsers.vue')

export default [
  {
    path: '/bank',
    component: Layout,
    redirect: 'chat',
    name: 'bank',
    meta: {
      title: 'bank',
    },
    icon: 'icon-home',
    children: [
      {
        path: '/customProduct',
        name: 'customProduct',
        component: CustomProduct,
        meta: {
          title: 'customProduct',
          affix: false,
        },
      },
      {
        path: '/customList',
        name: 'customList',
        component: CustomList,
        meta: {
          title: 'customList',
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
