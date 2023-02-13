package model

import "time"

type ItemModel struct {
	ID         uint `json:"item_id"`
	Title      string
	Content    string
	Status     int       `gorm:"default:1"`
	CreateTime time.Time `gorm:"type:datetime;autoCreateTime"`
	UpdateTime time.Time `gorm:"type:datetime;autoUpdateTime"`
}

// 自定义表名
func (ItemModel) TableName() string {
	return "tb_item"
}
