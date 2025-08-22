package com.xuxiaolei.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查询参数工具类
 * 把前端传过来的 page / limit 转换成 MyBatis-Plus 的 Page 对象
 */
public class Query<T> extends LinkedHashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    // 当前页码
    private int page;
    // 每页条数
    private int limit;

    public Query(Map<String, Object> params) {
        this.putAll(params);

        // 分页参数
        this.page = Integer.parseInt(params.getOrDefault("page", "1").toString());
        this.limit = Integer.parseInt(params.getOrDefault("limit", "10").toString());

        // 计算偏移量（可用于手写 SQL 分页）
        this.put("offset", (page - 1) * limit);
        this.put("page", page);
        this.put("limit", limit);
    }

    /**
     * 生成 MyBatis-Plus 分页对象
     */
    public IPage<T> getPage() {
        return new Page<>(page, limit);
    }

    /**
     * 兼容老用法：把 params 直接传进来
     */
    public IPage<T> getPage(Map<String, Object> params) {
        return new Query<T>(params).getPage();
    }

    public int getPageNum() {
        return page;
    }

    public int getLimit() {
        return limit;
    }
}
