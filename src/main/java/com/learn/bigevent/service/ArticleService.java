package com.learn.bigevent.service;

import com.learn.bigevent.pojo.Article;
import com.learn.bigevent.pojo.PageBean;

public interface ArticleService {
    void add(Article article);

    // 条件分页列表查询
    PageBean<Article> list(Integer pageNum, Integer pageSize, String state, Integer categoryId);

    void update(Article article);

    Article findById(Integer id);

    void delete(Integer id);
}
