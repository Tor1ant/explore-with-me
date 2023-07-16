package com.github.explore_with_me.main.category.service;

import com.github.explore_with_me.main.paramEntity.PaginationParams;
import com.github.explore_with_me.main.category.dto.CategoryOutDto;
import com.github.explore_with_me.main.category.dto.NewCategoryDto;
import java.util.List;

public interface CategoryService {

    CategoryOutDto saveCategory(NewCategoryDto newCategoryDto);

    void deleteCategory(Long categoryId);

    CategoryOutDto updateCategory(Long categoryId, NewCategoryDto newCategoryDto);

    List<CategoryOutDto> getCategories(PaginationParams paginationParams);

    CategoryOutDto getCategoryById(Long id);
}
