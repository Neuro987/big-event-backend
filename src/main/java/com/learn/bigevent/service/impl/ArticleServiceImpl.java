package com.learn.bigevent.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.learn.bigevent.mapper.ArticleMapper;
import com.learn.bigevent.pojo.Article;
import com.learn.bigevent.pojo.PageBean;
import com.learn.bigevent.service.ArticleService;
import com.learn.bigevent.utils.ThreadLocalUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Override
    public void add(Article article) {


        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        Map<String, Object> map = ThreadLocalUtil.get();
        int userId = (Integer) map.get("id");
        article.setCreateUser(userId);
        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, String state, Integer categoryId) {
        PageBean<Article> pb = new PageBean<>();
        // Use PageHelper for pagination
        PageHelper.startPage(pageNum, pageSize);
        // Call the mapper method to get the list of articles
        Map<String, Object> map = ThreadLocalUtil.get();
        int userId = (Integer) map.get("id");
        List<Article> as =  articleMapper.list(state, categoryId, userId);
        Page<Article> p = (Page<Article>) as;

        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public void update(Article article) {
        article.setUpdateTime(LocalDateTime.now());
        articleMapper.update(article);
    }

    @Override
    public Article findById(Integer id) {
        return articleMapper.findById(id);
    }

    @Override
    public void delete(Integer id) {
        articleMapper.delete(id);
    }

}
