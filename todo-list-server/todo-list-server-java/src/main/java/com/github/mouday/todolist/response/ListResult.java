package com.github.mouday.todolist.response;

import lombok.Data;

import java.util.List;

@Data
public class ListResult<T>{
    private Long total;
    private List<T> list;

    public static <T> ListResult of(List<T> list, Long total){
        ListResult<T> listResult = new ListResult<>();
        listResult.setList(list);
        listResult.setTotal(total);
        return listResult;
    }
}