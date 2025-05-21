package edu.wixze.com.service.impl;

import edu.wixze.com.dto.Category;
import edu.wixze.com.entity.CategoryEntity;
import edu.wixze.com.repository.CategoryRepository;
import edu.wixze.com.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public Category addCategory(Category category) {
        CategoryEntity entity = modelMapper.map(category, CategoryEntity.class);
        return modelMapper.map(categoryRepository.save(entity), Category.class);
    }

    @Override
    public Category updateCategory(Category category) {
        CategoryEntity existing = categoryRepository.findById(category.getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        modelMapper.map(category, existing);
        return modelMapper.map(categoryRepository.save(existing), Category.class);
    }

    @Override
    public Boolean deleteCategory(Long id) {
        categoryRepository.deleteById(id);
        return true;
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(entity -> modelMapper.map(entity, Category.class))
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, Category.class))
                .collect(Collectors.toList());
    }
}