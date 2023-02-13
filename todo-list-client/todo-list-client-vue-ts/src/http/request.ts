import axios, { AxiosRequestConfig } from 'axios'
import { ElMessage } from 'element-plus'
import type { ResultModel } from '../models/ResultModel'

export const VITE_APP_API = import.meta.env.VITE_APP_API

const instance = axios.create({
  baseURL: VITE_APP_API,
})

const request = async <T = any>(
  config: AxiosRequestConfig
): Promise<ResultModel<T>> => {
  const { data } = await instance.request<ResultModel<T>>(config)

  return data
}

export default request
