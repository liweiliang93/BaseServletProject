package com.weiliang.entity;

import lombok.Data;

import java.util.List;

/**
 * @author liweiliang
 * @description 1.0
 * @date 2024-03-07 23:30:18
 */
@Data
public class PageBean<T> {
    // 总记录数
    private int totalCount;
    // 当前页数据
    private List<T> rows;
}
