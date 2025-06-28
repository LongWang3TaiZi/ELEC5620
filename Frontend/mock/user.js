export default [
  {
    url: '/api/recommend/list', // 请求地址
    method: 'get', // 请求方法
    response: ({ query }) => {
      // 响应内容
      return {
        code: 200,
        'data|10': [
          {
            id: () => Math.floor(Math.random() * 100),
            image: `https://picsum.photos/200/300?random=${Math.random()}`,
            name: `产品${Math.floor(Math.random() * 100)}`,
            details: `随机描述${Math.floor(Math.random() * 100)}`,
            contact: `123456789${Math.floor(Math.random() * 100)}`,
            'isCollect|1': [true, false],
          },
        ],
        total: 10,
      }
    },
  },
  {
    url: '/api/recommend/collect', 
    method: 'post', // 请求方法
    response: ({ query }) => {
      // 响应内容
      return {
        code: 200,
        msg: '成功',
      }
    },
  },
  {
    url: '/api/Report/list',
    method: 'get',
    response: ({ query }) => {
      // 响应内容
      return {
        code: 200,
        'data|10': [
          {
            id: () => Math.floor(Math.random() * 100),
            details: `随机描述${Math.floor(Math.random() * 100)}`,
          },
        ],
        total: 10,
      }
    },
  },
]
