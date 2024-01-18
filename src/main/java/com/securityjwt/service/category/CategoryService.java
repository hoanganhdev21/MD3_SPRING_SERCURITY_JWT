package com.securityjwt.service.category;

import com.securityjwt.model.dto.response.CategoryResponse;
import com.securityjwt.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    Page<CategoryResponse> getAll(Pageable pageable);
    Category findById(Long id);
    Category save(Category category);
    void delete(Long id);
}
