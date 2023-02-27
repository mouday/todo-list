package com.github.mouday.todolist.service;

import com.github.mouday.todolist.response.ListResult;
import com.github.mouday.todolist.entity.Item;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.mouday.todolist.entity.ItemDto;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mouday
 * @since 2023-02-23
 */
public interface ItemService extends IService<Item> {

    ListResult getItemListWithTotal(ItemDto itemDto);
}
