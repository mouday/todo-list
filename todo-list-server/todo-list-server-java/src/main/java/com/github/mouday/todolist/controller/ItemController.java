package com.github.mouday.todolist.controller;

import com.github.mouday.todolist.exception.AppException;
import com.github.mouday.todolist.response.JsonResponse;
import com.github.mouday.todolist.response.JsonResult;
import com.github.mouday.todolist.response.ListResult;
import com.github.mouday.todolist.constant.ItemStatusConst;
import com.github.mouday.todolist.entity.Item;
import com.github.mouday.todolist.entity.ItemDto;
import com.github.mouday.todolist.response.ResultCodeEnum;
import com.github.mouday.todolist.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author mouday
 * @since 2023-02-23
 */
@RestController
@JsonResponse
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/api/getItemList")
    public ListResult getItemList(@RequestBody ItemDto itemDto) {
        return itemService.getItemListWithTotal(itemDto);
    }

    @PostMapping("/api/addItem")
    public void addItem(@RequestBody Item params) {
        Item item = new Item();
        item.setTitle(params.getTitle());
        item.setContent(params.getContent());
        item.setStatus(ItemStatusConst.WAIT);

        itemService.save(item);
    }

    @PostMapping("/api/updateItemById")
    public void updateItemById(@RequestBody Item params) {
        Item item = new Item();
        item.setId(params.getId());
        item.setTitle(params.getTitle());
        item.setContent(params.getContent());

        itemService.updateById(params);
    }

    @PostMapping("/api/deleteItemById")
    public void deleteItemById(@RequestBody Item params) {
        Item item = new Item();
        item.setId(params.getId());
        item.setStatus(ItemStatusConst.DELETE);

        itemService.updateById(item);
    }
}
