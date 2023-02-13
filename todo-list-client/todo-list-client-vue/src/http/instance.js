import axios from 'axios'
import { ElMessage } from 'element-plus'

export const VITE_APP_API = import.meta.env.VITE_APP_API

const instance = axios.create({
  baseURL: VITE_APP_API,
})

// 请求拦截器
instance.interceptors.request.use(
  (config) => {
    return config
  },
  (err) => {
    console.log(err)
  }
)

// 添加响应拦截器
instance.interceptors.response.use(
  (res) => {
    return res.data
  },
  (err) => {
    console.log(err)
    ElMessage.error(err)
  }
)

export default instance
