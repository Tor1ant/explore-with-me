package com.github.explore_with_me.main.category.mapper;

import com.github.explore_with_me.main.category.dto.CategoryOutDto;
import com.github.explore_with_me.main.category.dto.NewCategoryDto;
import com.github.explore_with_me.main.category.model.Category;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category newCategoryDtoToCategory(NewCategoryDto newCategoryDto);

    CategoryOutDto categoryToCategoryOutDto(Category category);

    List<CategoryOutDto> categoriesToCategoriesOutDto(List<Category> paginatedCategories);
}
