package main

import (
	"embed"
	"io/fs"
	"net/http"
	"todo-list-server-go/api"
	"todo-list-server-go/database"
	"todo-list-server-go/model"

	"github.com/gin-gonic/gin"
)

//go:embed public/*
var local embed.FS

func main() {
	app := gin.Default()

	// 初始化数据表
	db := database.GetDB()

	if !db.Migrator().HasTable(&model.ItemModel{}) {
		db.Migrator().CreateTable(&model.ItemModel{})
	}

	// 简单的路由组
	app.POST("/api/getItemList", api.GetItemList)
	app.POST("/api/getItemById", api.GetItemById)
	app.POST("/api/addItem", api.AddItem)
	app.POST("/api/updateItemById", api.UpdateItemById)
	app.POST("/api/deleteItemById", api.DeleteItemById)

	// 【Go语言】gin + go:embed 打包静态资源文件
	// ref: https://blog.csdn.net/Regulations/article/details/128858670
	fp, _ := fs.Sub(local, "public")
	app.StaticFS("/", http.FS(fp))

	// 监听并在 127.0.0.1:8080 上启动服务
	app.Run("0.0.0.0:8080")
}
