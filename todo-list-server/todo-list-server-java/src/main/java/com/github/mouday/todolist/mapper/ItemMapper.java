package com.github.mouday.todolist.mapper;

import com.github.mouday.todolist.entity.Item;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mouday
 * @since 2023-02-23
 */
@Mapper
public interface ItemMapper extends BaseMapper<Item> {

}
