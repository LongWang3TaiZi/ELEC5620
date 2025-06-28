export default [
  {
    url: '/api/customer/list', // 请求地址
    method: 'get', // 请求方法
    response: ({ query }) => {
      // 响应内容
      return {
        code: 200,
        'data|10': [
          {
            id: '@id',
            name: '@cname',
            asset: '@csentence',
            contact: '@phone',
          },
        ],
        total: 10,
      }
    },
  },
  {
    url: '/api/customProduct/list',
    method: 'get',
    response: ({ query }) => {
      // 响应内容
      return {
        code: 200,
        'data|10': [
          {
            id: () => Math.floor(Math.random() * 1000) + 1,
            name: '@cname',
            description: '@csentence',
            contact: '@phone',
            image: '@image("200x100", "@color", "@word")',
          },
        ],
        total: 10,
      }
    },
  },
  {
    // 添加
    url: '/api/customProduct',
    method: 'post',
    response: ({ query }) => {
      // 响应内容
      return {
        code: 200,
        msg: '成功',
      }
    },
  },
  {
    // 修改
    url: '/api/customProduct',
    method: 'put',
    response: ({ query }) => {
      // 响应内容
      return {
        code: 200,
        msg: '成功',
      }
    },
  },
  {
    url: '/api/customProduct/:id',
    method: 'delete',
    response: ({ query }) => {
      // 响应内容
      return {
        code: 200,
        msg: '成功',
      }
    },
  },
]
