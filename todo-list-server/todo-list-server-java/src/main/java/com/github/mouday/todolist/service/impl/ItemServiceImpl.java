package com.github.mouday.todolist.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.mouday.todolist.response.ListResult;
import com.github.mouday.todolist.constant.ItemStatusConst;
import com.github.mouday.todolist.entity.Item;
import com.github.mouday.todolist.entity.ItemDto;
import com.github.mouday.todolist.mapper.ItemMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.mouday.todolist.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author mouday
 * @since 2023-02-23
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {
    public ListResult getItemListWithTotal(ItemDto itemDto) {
        LambdaQueryWrapper<Item> queryWrapper = new LambdaQueryWrapper<>();

        // 查询条件
        Integer status = itemDto.getStatus();
        if (status != null && !ItemStatusConst.ALL.equals(status)) {
            queryWrapper.eq(Item::getStatus, itemDto.getStatus());
        }

        // 排序
        queryWrapper.orderByDesc(Item::getCreateTime)
                .orderByDesc(Item::getId);

        // 翻页
        Page<Item> pageInfo = new Page<>(itemDto.getPage(), itemDto.getSize());

        super.page(pageInfo, queryWrapper);

        long total = pageInfo.getTotal();
        List<Item> records = pageInfo.getRecords();
        ListResult listResult = ListResult.of(records, total);

        return listResult;
    }
}
