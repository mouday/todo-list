package com.github.mouday.todolist.entity;

import com.github.mouday.todolist.constant.ItemStatusConst;
import lombok.Data;

@Data
public class ItemDto {
    private Integer page = 1;

    private Integer size = 10;

    private Integer  status = ItemStatusConst.ALL;
}
