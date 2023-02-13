package api

import (
	"fmt"
	"net/http"
	"todo-list-server-go/database"
	"todo-list-server-go/model"
	"todo-list-server-go/utils"

	"github.com/gin-gonic/gin"
)

func GetItemList(ctx *gin.Context) {
	params := utils.PageStruct{
		Page:   1,
		Size:   10,
		Status: 0,
		Order:  "create_time desc",
	}

	ctx.BindJSON(&params)

	db := database.GetDB()

	var total int64
	var list []model.ItemVo

	where := model.ItemModel{}
	if params.Status != 0 {
		where.Status = params.Status
	}
	db.Model(&model.ItemModel{}).Where(&where).Order(params.Order).Limit(params.Size).Offset((params.Page - 1) * params.Size).Find(&list)

	db.Model(&model.ItemModel{}).Where(&where).Count(&total)

	ctx.JSON(http.StatusOK, utils.MakeResult(map[string]interface{}{
		"total": total,
		"list":  list,
	}))
}

func GetItemById(ctx *gin.Context) {
	params := make(map[string]interface{})
	ctx.BindJSON(&params)

	db := database.GetDB()

	var user model.ItemModel

	// 根据主键检索
	db.First(&user, params["item_id"])

	ctx.JSON(http.StatusOK, utils.MakeResult(user))
}

func AddItem(ctx *gin.Context) {
	item := model.ItemModel{}
	ctx.BindJSON(&item)

	item.Status = 1 // 待处理

	// 通过数据的指针来创建
	database.GetDB().Create(&item)

	ctx.JSON(http.StatusOK, utils.MakeResult(item))
}

func UpdateItemById(ctx *gin.Context) {
	item := model.ItemModel{}

	ctx.BindJSON(&item)

	fmt.Println("item", item)

	db := database.GetDB()

	db.Model(&model.ItemModel{}).Where("id = ?", item.ID).Updates(item)

	ctx.JSON(http.StatusOK, utils.MakeResult(nil))
}

func DeleteItemById(ctx *gin.Context) {
	// 匿名struct
	params := struct {
		ItemId int `json:"item_id"`
	}{}

	ctx.BindJSON(&params)

	// fmt.Println("params", params)

	db := database.GetDB()

	// db.Where("id = ?", params.ItemId).Delete(&model.ItemModel{})

	db.Model(&model.ItemModel{}).Where("id = ?", params.ItemId).Update("status", 3)

	ctx.JSON(http.StatusOK, utils.MakeResult(nil))
}
