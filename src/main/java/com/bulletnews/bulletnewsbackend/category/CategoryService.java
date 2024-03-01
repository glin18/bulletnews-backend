package com.bulletnews.bulletnewsbackend.category;

import com.bulletnews.bulletnewsbackend.category.dto.CategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category createCategory(CategoryDTO request) {
        Category category = new Category();
        BeanUtils.copyProperties(request, category);
        return categoryRepository.save(category);
    }

    public Category updateCategoryById(Long id, CategoryDTO request) {
        Category category = categoryRepository.findById(id).orElseThrow();
        category.setName(request.getName());
        category.setSearchTerm(request.getSearchTerm());
        return categoryRepository.save(category);
    }
}
