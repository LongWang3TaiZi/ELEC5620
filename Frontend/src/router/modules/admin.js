const Layout = () => import('@/layout/index.vue')
const AdminPage = () => import('@/views/pages/AdminPage.vue')
const Chat = () => import('@/components/Chat/index.vue')
const ManageUsers = () => import('@/views/admin/manageUsers.vue')
export default [
  {
    path: '/admin',
    component: Layout, // 使用相同的 Layout 包裹
    redirect: '/chat',
    name: 'AdminDashboard',
    meta: {
      title: 'menu.admin', // 在国际化中设置为 "Admin Page"
      icon: 'icon-settings', // 添加一个设置图标
    },
    children: [
      {
        path: '/chat',
        name: 'chat',
        component: Chat,
        meta: {
          title: 'Chat',
          affix: false,
        },
      },

      {
        path: '/manageUsers', // 添加 ManageUsers 路由
        name: 'manageUsers',
        component: ManageUsers,
        meta: {
          title: 'Manage Users', // 设置标题
          affix: false,
        },
      },
    ],
  },
]
