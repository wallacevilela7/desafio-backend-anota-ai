package tech.wvs.anotaaiapi.domain.category;

import tech.wvs.anotaaiapi.controller.category.dto.CategoryResponse;
import tech.wvs.anotaaiapi.controller.category.dto.CategoryRequest;

public class CategoryMapper {

    public static Category toEntity(CategoryRequest dto) {
        var entity = new Category();
        entity.setTitle(dto.title());
        entity.setDescription(dto.description());
        entity.setOwnerId(dto.ownerId());
        return entity;
    }

    public static CategoryResponse toResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getTitle(),
                category.getDescription(),
                category.getOwnerId()
        );
    }
}
