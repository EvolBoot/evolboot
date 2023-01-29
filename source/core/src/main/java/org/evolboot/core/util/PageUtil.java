package org.evolboot.core.util;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 * 参考人人分页工具
 */
@Data
public class PageUtil<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    //总记录数
    private long total;
    //每页记录数
    private long size;
    //总页数
    private long totalPage;
    //当前页数
    private long page;
    //列表数据
    private List<T> list;

    /**
     * 分页
     *
     * @param list       列表数据
     * @param totalCount 总记录数
     * @param pageSize   每页记录数
     * @param currPage   当前页数
     */
    public PageUtil(List<T> list, long totalCount, long pageSize, long currPage) {
        this.list = list;
        this.total = totalCount;
        this.size = pageSize;
        this.page = currPage;
        this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
    }

    public PageUtil<T> convert(List<T> list, long totalCount, long pageSize, long offset) {
        this.list = list;
        this.total = totalCount;
        this.size = pageSize;
        if (offset % pageSize > 0) {
            this.page = offset / pageSize + 1L;
        } else {
            this.page = offset / pageSize;
        }
        this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
        return this;
    }

    private PageUtil() {
    }

    public static <T> PageUtil<T> convertPage(List<T> list, long totalCount, long pageSize, long offset) {
        return new PageUtil<T>().convert(list, totalCount, pageSize, offset);
    }


}
