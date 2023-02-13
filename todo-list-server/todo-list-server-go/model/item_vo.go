package model

import "todo-list-server-go/utils"

type ItemVo struct {
	ID         uint                `json:"item_id"`
	Title      string              `json:"title"`
	Content    string              `json:"content"`
	Status     int                 `json:"status"`
	CreateTime utils.DateTimeLabel `json:"create_time"`
	UpdateTime utils.DateTimeLabel `json:"update_time"`
}
