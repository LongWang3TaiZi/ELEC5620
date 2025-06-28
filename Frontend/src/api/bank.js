import request from '@/utils/request'
// 顾客列表
// 传入参数 {  pageSize: 10,  pageNum: 1,}
// 返回参数 {
// code: 200
// data: [{  id: 1,
// name: 'product1.jpg',
// asset: '金融产品 1',
// contact: '产品详情 1'}]
// total: 10
// }
export const fetchCustomerList = data => {
  return request({
    url: '/api/customer/ids-names',
    method: 'get',
    params: data,
  })
}

// 定制产品
// 传入参数 {  pageSize: 10,  pageNum: 1,}
// 返回参数 {
// code: 200
// data: [{  id: 1,
//   image: '',
//   name: '',
//   description: '',
//   contact: '',}]
// total: 10
// }
export const fetchCustomProductList = () => {
  return request({
    url: '/api/products',
    method: 'get',
  })
}
// 传入参数 {   image: '',
//   name: '',
//   description: '',
//   contact: '',}
// 返回{code : 200 ,msg:'成功'}
export const fetchAddCustomProduct = data => {
  return request({
    url: '/api/products',
    method: 'post',
    data,
  })
}
// 传入参数 {
//   id: '',
//   image: '',
//   name: '',
//   description: '',
//   contact: '',}
// 返回{code : 200 ,msg:'成功'}
export const fetchUpdateCustomProduct = (id, data) => {
  return request({
    url: `/api/products/${id}`,
    method: 'put',
    data,
  })
}
// 传入参数 id
// 返回{code : 200 ,msg:'成功'}
export const fetchDelCustomProduct = id => {
  return request({
    url: `/api/products/${id}`,
    method: 'delete',
  })
}
