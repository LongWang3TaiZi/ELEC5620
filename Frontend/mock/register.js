import Mock from 'mockjs';

// 定义全局变量来存储注册时的用户角色和用户名信息
let registeredUserRole = null;
let registeredUserName = null;

export default [
  {
    url: '/api/register',
    method: 'post',
    timeout: 1000,
    statusCode: 200,
    response: ({ body }) => {
      const { userName, email, password } = body;

      console.log('userName:', userName);
      console.log('Email:', email);
      console.log('Password:', password);

      // 检查请求体中是否包含必要的字段
      if (!userName || !email || !password) {
        return {
          code: 400,
          message: '注册信息不完整',
        };
      }

      // 模拟用户角色，根据用户名决定用户的角色
      if (userName === 'admin') {
        registeredUserRole = 'admin';
        registeredUserName = 'Admin User';
      } else if (userName === 'bank') {
        registeredUserRole = 'bank';
        registeredUserName = 'Bank User';
      } else {
        registeredUserRole = 'user';
        registeredUserName = 'Normal User';
      }

      // 模拟成功的返回值
      return {
        code: 200,
        message: '注册成功',
        data: {
          userName: registeredUserName,  // 存储用户名
          role: registeredUserRole,  // 返回角色信息
          token: Mock.Random.guid(),  // 使用 Mock.js 生成随机 token
        },
      };
    },
  },
  {
    url: '/api/userinfo',
    method: 'get',
    timeout: 100,
    response: {
      code: 200,
      message: 'Get user information successfully',
      data: {
        id: 1,
        name: registeredUserName,  // 返回注册时存储的用户名
        role: registeredUserRole,  // 返回注册时存储的角色
        avatar: "@image('48x48', '#fb0a2a')", // 随机生成头像
      },
    },
  },
];
