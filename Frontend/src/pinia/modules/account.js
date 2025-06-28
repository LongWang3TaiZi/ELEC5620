import { defineStore } from 'pinia'
import { GetUserinfo } from '@/api/login'
import { getItem, setItem } from '@/utils/storage'

export const useAccount = defineStore('account', {
  state: () => ({
    userinfo: null,
    permissionList: [],
  }),
  actions: {
    // 清除用户信息
    clearUserinfo() {
      console.log('Clearing userinfo...') // 添加日志
      this.userinfo = null
      console.log('Userinfo after clearing:', this.userinfo) // 确认清除后的状态
    },

    // 获取用户信息
    async getUserinfo() {
      console.log('Fetching userinfo...') // 添加日志
      // const res = await GetUserinfo(getItem('userInfo').id ?? '')

      try {
        const res = await GetUserinfo(getItem('userInfo').id)
        console.log('API response:', res) // 打印 API 返回的 code 和 data
        if (res.id) {
          this.userinfo = res
          setItem('userInfo', res)
          // console.log('Userinfo successfully fetched and set:', this.userinfo) // 确认获取成功后的用户信息
          return Promise.resolve(res.user)
        } else {
          console.error(
            'Failed to fetch userinfo. Error code:',
            res.responseCode
          )
          return Promise.reject(new Error('Failed to fetch userinfo'))
        }
      } catch (error) {
        console.error('Error fetching userinfo:', error) // 捕获异常并打印
        return Promise.reject(error)
      }
    },
  },
})
