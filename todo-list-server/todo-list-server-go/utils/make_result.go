package utils

import "github.com/gin-gonic/gin"

// go gin 动态解析post的JSON数据
// https://blog.csdn.net/lishuangquan1987/article/details/123519817
// 统一的返回
func MakeResult(data interface{}) gin.H {
	return gin.H{
		"msg":  "success",
		"code": 0,
		"data": data,
	}
}
