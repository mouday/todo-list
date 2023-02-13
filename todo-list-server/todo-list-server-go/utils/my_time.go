package utils

import (
	"fmt"
	"time"
)

// ref https://blog.csdn.net/weixin_44934844/article/details/115375154
// https://www.cnblogs.com/tl542475736/p/12020822.html
// MyTime 自定义时间

type DateTimeLabel time.Time

// 格式化日期
func (str DateTimeLabel) MarshalJSON() ([]byte, error) {
	var stamp string
	start_time := time.Now().Unix()
	end_time := time.Time(str).Unix()
	diff_time := start_time - end_time
	if diff_time <= 60 {
		stamp = `"刚刚"`
		return []byte(stamp), nil
	} else if diff_time > 60 && diff_time <= 3600 {
		stamp = fmt.Sprintf(`"%d分钟前"`, int(diff_time/60))
		return []byte(stamp), nil
	} else if diff_time > 3600 && diff_time <= 86400 {
		stamp = fmt.Sprintf(`"%d小时前"`, int(diff_time/3600))
		return []byte(stamp), nil
	} else {
		stamp = fmt.Sprintf(`"%d天前"`, int(diff_time/86400))
		return []byte(stamp), nil
	}
}
