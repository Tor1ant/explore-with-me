package com.github.explore_with_me.main.category.service;

import com.github.explore_with_me.main.category.controller.paramEntity.CategoryParams;
import com.github.explore_with_me.main.category.dto.CategoryOutDto;
import com.github.explore_with_me.main.category.dto.NewCategoryDto;
import com.github.explore_with_me.main.category.mapper.CategoryMapper;
import com.github.explore_with_me.main.category.model.Category;
import com.github.explore_with_me.main.category.repository.CategoryRepository;
import com.github.explore_with_me.main.exception.model.ConflictException;
import com.github.explore_with_me.main.exception.model.NotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Transactional
    @Override
    public CategoryOutDto saveCategory(NewCategoryDto newCategoryDto) {
        Category category = categoryMapper.newCategoryDtoToCategory(newCategoryDto);
        try {
            categoryRepository.save(category);
        } catch (Exception e) {
            handleConflictException(e);
        }
        log.info("Категория= " + category + " сохранена");
        return categoryMapper.CategoryToCategoryOutDto(category);
    }

    @Transactional
    @Override
    public void deleteCategory(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new NotFoundException("Категория с id= " + categoryId + " не найдена");
        }
        try {
            categoryRepository.deleteById(categoryId);
        } catch (Exception e) {
            throw new ConflictException(e.getMessage());
        }
        log.info("Категория с id= " + categoryId + " удалена");
    }

    @Transactional
    @Override
    public CategoryOutDto updateCategory(Long categoryId, NewCategoryDto newCategoryDto) {
        Optional<Category> oldCategory = categoryRepository.findById(categoryId);
        if (oldCategory.isEmpty()) {
            throw new NotFoundException("Категория с id= " + categoryId + " не найдена");
        }
        Category updatedCategory = categoryMapper.newCategoryDtoToCategory(newCategoryDto);
        try {
            categoryRepository.save(updatedCategory);

        } catch (Exception e) {
            handleConflictException(e);
        }
        updatedCategory.setId(categoryId);
        log.info("Категория с id= " + categoryId + " изменила название с = " + oldCategory.get().getName() + " на = "
                + newCategoryDto.getName());
        return categoryMapper.CategoryToCategoryOutDto(updatedCategory);
    }

    private void handleConflictException(Exception e) {
        StringBuilder stringBuilder = new StringBuilder(e.getCause().getCause().getMessage());
        int indexEqualsSign = stringBuilder.indexOf("=");
        stringBuilder.delete(0, indexEqualsSign + 1);
        throw new ConflictException(stringBuilder.toString().replace("\"", "").trim());
    }

    @Override
    public List<CategoryOutDto> getCategories(CategoryParams categoryParams) {
        PageRequest pagination = PageRequest.of(categoryParams.getFrom() / categoryParams.getSize(),
                categoryParams.getSize());
        List<Category> paginatedCategories = categoryRepository.findAll(pagination).getContent();
        log.info("Получен список категорий= " + paginatedCategories);
        return categoryMapper.CategoriesToCategoriesOutDto(paginatedCategories);
    }

    @Override
    public CategoryOutDto getCategoryById(Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isEmpty()) {
            throw new NotFoundException("Категория с id= " + categoryId + " не найдена");
        }
        CategoryOutDto categoryDto = categoryMapper.CategoryToCategoryOutDto(category.get());
        log.info("Получена категория= " + category.get());
        return categoryDto;
    }
}