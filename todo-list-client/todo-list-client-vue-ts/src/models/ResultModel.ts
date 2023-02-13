export interface ResultModel<T = any> {
  msg: string
  code: number
  data: T
}
