package tech.wvs.anotaaiapi.service;

import org.springframework.stereotype.Service;
import tech.wvs.anotaaiapi.controller.category.dto.CategoryRequest;
import tech.wvs.anotaaiapi.controller.category.dto.CategoryUpdateRequest;
import tech.wvs.anotaaiapi.domain.category.Category;
import tech.wvs.anotaaiapi.domain.category.CategoryMapper;
import tech.wvs.anotaaiapi.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category create(CategoryRequest dto) {
        var entity = CategoryMapper.toEntity(dto);
        return categoryRepository.save(entity);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(String id) {
        return categoryRepository.findById(id);
    }

    public Category update(String id, CategoryUpdateRequest dto) {
        var entity = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        updateFields(dto, entity);
        return categoryRepository.save(entity);
    }

    private static void updateFields(CategoryUpdateRequest dto, Category entity) {
        if (dto.title() != null && !dto.title().isBlank())
            entity.setTitle(dto.title());

        if (dto.description() != null && !dto.description().isBlank())
            entity.setDescription(dto.description());
    }

    public boolean delete(String id) {
        var exists = categoryRepository.existsById(id);
        if (exists)
            categoryRepository.deleteById(id);

        return exists;
    }
}
