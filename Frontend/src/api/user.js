import request from '@/utils/request'
//推荐
// 传入参数 {  pageSize: 10,  pageNum: 1,}
// 返回参数 {
// code: 200
// data: [{  id: 1,
// image: 'product1.jpg',
// name: '金融产品 1',
// details: '产品详情 1',
// contact: '联系方式 1',}]
// total: 10
// }
export const fetchGetRecommendList = data => {
  return request({
    url: `/api/products`,
    method: 'get',
    params: data,
  })
}
// 传入参数 {  isCollect: true | false }
// 返回{code : 200 ,msg:'成功'}
// 收藏
export const fetchGetRecommendCollect = (customerId, data) => {
  return request({
    url: `/api/customer/${customerId}/favorites/add`,
    method: 'post',
    data,
  })
}

// 传入参数 {  pageSize: 10,  pageNum: 1,deposit: '',liability: '',income: '',fixedAssets: '',}
// 返回参数 {
// code: 200
// data: [{  id: 1,
// createdAt: '生成时间',
// details: '报告内容'}]
// total: 10
// }
// 分析报告
export const fetchGetReportList = (customerId, data) => {
  return request({
    url: `/api/customer/analyzeAssets/${customerId}`,
    method: 'post',
    data,
  })
}
