import request from '@/utils/request'
export async function register(data) {
  try {
    const response = await fetch('http://localhost:8081/api/user/register', {
      // 确保URL正确
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(data),
    })
    if (!response.ok) {
      throw new Error('Username already exists. Please try a different one.')
    }
    return await response.json()
  } catch (error) {
    throw new Error(error.message || 'Failed to register')
  }
}
