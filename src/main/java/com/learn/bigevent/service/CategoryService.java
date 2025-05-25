package com.learn.bigevent.service;

import com.learn.bigevent.pojo.Category;

import java.util.List;

public interface CategoryService {
    void add(Category category);

    // Retrieve all categories
    List<Category> list();

    // Retrieve category details by ID
    Category findById(Integer id);

    void update(Category category);

    void delete(Integer id);
}
