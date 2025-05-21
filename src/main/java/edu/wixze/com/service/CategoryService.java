package edu.wixze.com.service;

import edu.wixze.com.dto.Category;

import java.util.List;

public interface CategoryService {
    Category addCategory(Category category);
    Category updateCategory(Category category);
    Boolean deleteCategory(Long id);
    Category getCategoryById(Long id);
    List<Category> getAllCategories();
}
