import request from '@/utils/request'

// 发送验证码 API
export function sendCode(data) {
  return request({
    url: '/api/sendCode',
    method: 'post',
    data,
  })
}
