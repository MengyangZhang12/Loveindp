package com.project.loveindp.request;

public class PageQuery {

    // 页码
    private Integer page = 1;

    // 每页查询条数
    private Integer size = 10;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }



}
