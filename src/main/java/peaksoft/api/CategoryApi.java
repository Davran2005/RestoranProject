package peaksoft.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.category.CategoryRequest;
import peaksoft.dto.category.CategoryResponse;

import peaksoft.service.CategoryService;


import java.util.List;
@RestController
@RequestMapping("/api/restaurant/category")
@RequiredArgsConstructor
public class CategoryApi {
    private final CategoryService categoryService;

    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @Operation(description = "token", summary = "save")
    public SimpleResponse save(@RequestBody CategoryRequest request){
        return categoryService.saveCategory(request);
    }

    @PostMapping("/getAll")
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF','WAITER')")
    @Operation(description = "token", summary = "get all category")
    public List<CategoryResponse>getAll(){
        return categoryService.getAllCategory();
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("delete/{id}")
    @Operation(description = "token", summary = "delete")
    public SimpleResponse delete(@PathVariable Long id){
        return categoryService.deleteById(id);
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF','WAITER')")
    @Operation(description = "token", summary = "get by id")
    public CategoryResponse getById(@PathVariable Long id){
        return categoryService.getById(id);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(description = "token", summary = "update")
    public SimpleResponse update(@PathVariable Long id,@RequestBody CategoryRequest request){
        return categoryService.update(id,request);
    }

}
