package com.bulletnews.bulletnewsbackend.category;

import com.bulletnews.bulletnewsbackend.category.dto.CategoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("")
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    @PostMapping("")
    public CategoryDTO create(@RequestBody CategoryDTO request) {
        return categoryService.createCategory(request);
    }

    @PutMapping("/{id}")
    public CategoryDTO updateCategoryById(@RequestBody CategoryDTO categoryDto, @PathVariable Long id) {
        return categoryService.updateCategoryById(id, categoryDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
    }

}
