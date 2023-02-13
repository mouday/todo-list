package database

import (
	// "gorm.io/driver/sqlite" // 基于 GGO 的 Sqlite 驱动
	"github.com/glebarez/sqlite" // 纯 Go 实现的 SQLite 驱动, 详情参考： https://github.com/glebarez/sqlite
	"gorm.io/gorm"
	"gorm.io/gorm/logger"
)

func GetDB() *gorm.DB {
	db, _ := gorm.Open(sqlite.Open("database.db"), &gorm.Config{
		// 日志级别
		// Logger: logger.Default.LogMode(logger.Error),
		Logger: logger.Default.LogMode(logger.Info),
	})

	return db
}
