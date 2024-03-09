package com.weiliang.entity;

import java.util.List;
import java.util.Objects;

/**
 * @author liweiliang
 * @description 1.0
 * @date 2024-03-07 23:30:18
 */
public class PageBean<T> {
    // 总记录数
    private int totalCount;
    // 当前页数据
    private List<T> rows;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", rows=" + rows +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageBean<?> pageBean = (PageBean<?>) o;
        return totalCount == pageBean.totalCount && Objects.equals(rows, pageBean.rows);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalCount, rows);
    }
}
