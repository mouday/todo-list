package utils

// 可复用的列表翻页请求参数结构体
type PageStruct struct {
	Page   int    `json:"page"`
	Size   int    `json:"size"`
	Status int    `json:"status"`
	Order  string `json:"order"`
}
