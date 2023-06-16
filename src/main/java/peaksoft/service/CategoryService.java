package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.category.CategoryRequest;
import peaksoft.dto.category.CategoryResponse;
import peaksoft.entity.Category;

import java.util.List;

public interface CategoryService {
    SimpleResponse saveCategory(CategoryRequest request);
    List<CategoryResponse> getAllCategory();
    CategoryResponse getById(Long id);
    SimpleResponse deleteById(Long id);
    SimpleResponse update(Long id,CategoryRequest request);
}
