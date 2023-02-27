import axios, { AxiosRequestConfig } from 'axios'
import { ElMessage } from 'element-plus'
import type { ResultModel } from '../models/ResultModel'
import { ApiResultCode } from '../enums/ApiResultCode'

export const VITE_APP_API = import.meta.env.VITE_APP_API

const instance = axios.create({
  baseURL: VITE_APP_API,
})

const request = async <T = any>(
  config: AxiosRequestConfig
): Promise<ResultModel<T> | undefined> => {
  try {
    const res = await instance.request<ResultModel<T>>(config)

    if (res.data && res.data.code == ApiResultCode.SUCCESS) {
      // 成功
      return res.data
    } else {
      // 错误
      ElMessage.error(res.data.msg)
      throw new Error(res.data.msg)
    }
  } catch (e) {
    // 异常
    ElMessage.error('请求失败')
    throw e
  }
}

export default request
