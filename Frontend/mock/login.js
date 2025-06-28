// 定义全局变量来存储登录时的用户角色和用户名信息
let currentUserRole = null;
let currentUserName = null;

export default [
  {
    url: '/api/login',
    method: 'post',
    timeout: 1000,
    statusCode: 200,
    response: ({ body }) => {
      const { userName, password } = body;

      console.log('userName:', userName);
      console.log('Password:', password);

      let user = {};

      // 模拟三种不同角色的登录
      if (userName === 'admin' && password === '123456') {
        console.log('Login as admin');
        currentUserRole = 'admin'; // 存储当前用户的角色
        currentUserName = 'Admin User'; // 存储当前用户的名字
        user = {
          id: 24,
          email: 'admin@example.com',
          username: 'adminuser',
          role: 'admin',
          customer: {
            id: 188,
            customerName: 'adminuser',
            financialDetail: null,
          },
          bank: null,
        };
        return {
          message: 'User login successfully',
          responseCode: 'A1',
          user,
        };
      } else if (userName === 'bank' && password === '123456') {
        console.log('Login as bank');
        currentUserRole = 'bank'; // 存储当前用户的角色
        currentUserName = 'Bank User'; // 存储当前用户的名字
        user = {
          id: 24,
          email: 'bank@example.com',
          username: 'bankuser',
          role: 'bank',
          customer: {
            id: 188,
            customerName: 'bankuser',
            financialDetail: null,
          },
          bank: {
            id: 1,
            bankName: 'Mock Bank',
            location: 'Somewhere',
          },
        };
        return {
          message: 'User login successfully',
          responseCode: 'A1',
          user,
        };
      } else if (userName === 'user' && password === '123456') {
        console.log('Login as user');
        currentUserRole = 'customer'; // 存储当前用户的角色
        currentUserName = 'Normal User'; // 存储当前用户的名字
        user = {
          id: 24,
          email: 'user@example.com',
          username: 'testuser',
          role: 'customer',
          customer: {
            id: 188,
            customerName: 'testuser',
            financialDetail: null,
          },
          bank: null,
        };
        return {
          message: 'User login successfully',
          responseCode: 'A1',
          user,
        };
      } else {
        console.log('Login failed');
        return {
          code: 400,
          message: 'Incorrect userName or password', // 登录失败
        };
      }
    },
  },
  {
    url: '/api/userinfo',
    method: 'get',
    timeout: 100,
    response: () => ({
      code: 200,
      message: 'Get user information successfully',
      data: {
        id: 1,
        username: currentUserName,  // 返回与登录时相同的用户名
        role: currentUserRole,  // 返回与登录时相同的角色
        email: currentUserRole === 'admin' ? 'admin@example.com' : currentUserRole === 'bank' ? 'bank@example.com' : 'user@example.com',
        customer: currentUserRole === 'customer' ? { id: 188, customerName: currentUserName, financialDetail: null } : null,
        bank: currentUserRole === 'bank' ? { id: 1, bankName: 'Mock Bank', location: 'Somewhere' } : null,
      },
    }),
  },
];
