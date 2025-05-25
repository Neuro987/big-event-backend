package com.learn.bigevent.service.impl;

import com.learn.bigevent.mapper.CategoryMapper;
import com.learn.bigevent.pojo.Category;
import com.learn.bigevent.service.CategoryService;
import com.learn.bigevent.utils.ThreadLocalUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public void add(Category category) {

        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());

        Map<String, Object> map = ThreadLocalUtil.get();
        int id = (Integer) map.get("id");
        category.setCreateUser(id);

        categoryMapper.add(category);
    }

    @Override
    public List<Category> list() {

        Map<String, Object> map = ThreadLocalUtil.get();
        int id = (Integer) map.get("id");

        return categoryMapper.list(id);
    }

    @Override
    public Category findById(Integer id) {
        return categoryMapper.findById(id);
    }

    @Override
    public void update(Category category) {
        categoryMapper.update(category);
    }

    @Override
    public void delete(Integer id) {
        categoryMapper.delete(id);
    }
}
