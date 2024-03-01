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
    public List<Category> findAll(){
        return categoryService.findAll();
    }

    @PostMapping("")
    public Category create(@RequestBody CategoryDTO request){
        return categoryService.createCategory(request);
    }

    @PutMapping("/{id}")
    public Category updateCategoryById(@RequestBody CategoryDTO categoryDto, @PathVariable Long id){
        return categoryService.updateCategoryById(id, categoryDto);
    }

}
