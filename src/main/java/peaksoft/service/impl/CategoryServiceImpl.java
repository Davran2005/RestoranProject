package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.category.CategoryRequest;
import peaksoft.dto.category.CategoryResponse;
import peaksoft.entity.Category;
import peaksoft.exception.BadRequestException;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.CategoryRepository;
import peaksoft.service.CategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public SimpleResponse saveCategory(CategoryRequest request) {
        List<Category> categories = categoryRepository.findAll().stream().filter(category -> category.getName().equalsIgnoreCase(request.getName())).toList();
        Category category = new Category();
        if (categories.size() == 0) {
            if (category.getName().equalsIgnoreCase(request.getName())) {
                throw new BadRequestException("There are categories with this name");
            } else {
                categoryRepository.save(category);
                return SimpleResponse.builder()
                        .status(HttpStatus.OK)
                        .message("Успешно")
                        .build();
            }
        } else {
            throw new BadRequestException("Can't be name is 0");
        }
    }

    @Override
    public List<CategoryResponse> getAllCategory() {
        return categoryRepository.getAllCategory();
    }

    @Override
    public CategoryResponse getById(Long id) {
        return categoryRepository.getCaregoryById(id).orElseThrow(() ->
                new NotFoundException(String.format("Category with id: " + id + "is not found")));
    }

    @Override
    public SimpleResponse deleteById(Long id) {
        try {
            Category category = categoryRepository.findById(id).orElseThrow(() ->
                    new NotFoundException(String.format("Category with id: " + id + "is not found")));
            categoryRepository.delete(category);
        }catch (NotFoundException e){
            System.err.println(e.getMessage());
        }
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message("Успешно")
                .build();
    }

    @Override
    public SimpleResponse update(Long id, CategoryRequest request) {
        Category category = categoryRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Category with id: " + id + "is not found")));
        if (category.getName().equalsIgnoreCase(request.getName())) {
            throw new BadRequestException("There are categories with this name");
        } else {
            categoryRepository.save(category);
            return SimpleResponse.builder()
                    .status(HttpStatus.OK)
                    .message("Успешно")
                    .build();
        }
    }
}

