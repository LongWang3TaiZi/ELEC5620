// 随机生成用户工具函数
const generateUsers = (count) => {
    const roles = ['bank user', 'admin'];
    const users = [];
  
    for (let i = 1; i <= count; i++) {
      users.push({
        id: i,
        username: `User${i}`,
        email: `user${i}@example.com`,
        role: roles[Math.floor(Math.random() * roles.length)],
      });
    }
  
    return users;
  };
  
  // 生成100个用户
  let allUsers = generateUsers(100);
  
  export default [
    {
      // 获取用户列表
      url: '/api/users',
      method: 'get',
      response: ({ query }) => {
        const page = query.page ? parseInt(query.page) : 1;
        const size = query.size ? parseInt(query.size) : 20;
  
        // 分页实现
        const start = (page - 1) * size;
        const end = start + size;
        const pagedUsers = allUsers.slice(start, end);
  
        return {
          code: 200,
          data: pagedUsers,
          total: allUsers.length, // 返回总用户数用于前端分页
        };
      },
    },
    {
      // 更新用户信息
      url: '/api/users/:id',
      method: 'put',
      response: ({ query, body }) => {
        const { id } = query;
        const userIndex = allUsers.findIndex((user) => user.id === parseInt(id));
  
        if (userIndex === -1) {
          return {
            code: 404,
            message: 'User not found',
          };
        }
  
        // 更新用户信息
        allUsers[userIndex] = {
          ...allUsers[userIndex],
          username: body.username,
          email: body.email,
          role: body.role, // 允许更新用户角色
        };
  
        return {
          code: 200,
          message: `User with ID ${id} updated successfully.`,
          data: allUsers[userIndex],
        };
      },
    },
    {
      // 删除用户
      url: '/api/users/:id',
      method: 'delete',
      response: ({ query }) => {
        const { id } = query;
        const userIndex = allUsers.findIndex((user) => user.id === parseInt(id));
  
        if (userIndex === -1) {
          return {
            code: 404,
            message: 'User not found',
          };
        }
  
        // 删除用户
        allUsers = allUsers.filter((user) => user.id !== parseInt(id));
  
        return {
          code: 200,
          message: `User with ID ${id} deleted successfully.`,
        };
      },
    },
  ];
  