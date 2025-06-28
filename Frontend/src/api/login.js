// 登录接口
export const Login = async data => {
  try {
    const response = await fetch('http://localhost:8081/api/user/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(data),
    })

    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`)
    }

    const userData = await response.json() // 将响应转换为 JSON 格式
    if (!userData || !userData.responseCode) {
      throw new Error('Invalid response from server')
    }

    // 提取用户信息和角色
    const { responseCode, user, message } = userData
    if (responseCode === 'A1') {
      // 登录成功：存储用户信息和角色
      localStorage.setItem('role', user.role)
      localStorage.setItem('id', user.id)
      localStorage.setItem('userInfo', JSON.stringify(user))
      return userData
    } else {
      throw new Error(message || 'Login failed')
    }
  } catch (error) {
    console.error('Login error:', error)
    throw error // 将错误继续抛出，以便在组件中捕获
  }
}

// 获取指定用户信息接口
// 获取指定用户信息接口
// 获取指定用户信息接口
// export const GetUserinfo = async id => {
//   // 定义包含默认用户数据的对象
//   const mockResponse = {
//     code: 200,
//     message: 'Get user information successfully',
//     data: {
//       responseCode: 'A1',
//       user: {
//         id: 24,
//         email: 'test@example.com',
//         username: 'testuser',
//         role: 'customer',
//         customer: {
//           id: 188,
//           customerName: 'testuser',
//           financialDetail: null,
//         },
//         bank: null,
//       },
//     },
//   }

//   // 将用户信息存储到 localStorage
//   localStorage.setItem('userInfo', JSON.stringify(mockResponse.data))

//   return mockResponse // 返回包含 code, message, data 的默认响应
// }
export const GetUserinfo = async id => {
  try {
    const response = await fetch(
      `http://localhost:8081/api/user/userinfo/${id}`,
      {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
        },
      }
    )

    // 检查请求是否成功
    if (!response.ok) {
      throw new Error(`Failed to fetch user info, status: ${response.status}`)
    }

    const result = await response.json()

    // 检查响应数据格式是否正确
    if (
      result &&
      result.code === 200 &&
      result.data &&
      result.data.responseCode === 'A1'
    ) {
      // 将用户信息存储到 localStorage
      localStorage.setItem('userInfo', JSON.stringify(result.data))
      return result // 返回完整的响应数据
    } else {
      throw new Error(result.message || 'Failed to get user information')
    }
  } catch (error) {
    console.error('Error fetching user info:', error)
    throw error // 抛出错误以便在调用的地方处理
  }
}
