package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.category.CategoryResponse;
import peaksoft.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select new peaksoft.dto.category.CategoryResponse(c.id,c.name) from Category c")
    List<CategoryResponse> getAllCategory();

   Optional <CategoryResponse> getCaregoryById(Long id);
}