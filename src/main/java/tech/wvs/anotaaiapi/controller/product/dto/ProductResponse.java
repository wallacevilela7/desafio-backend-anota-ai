package tech.wvs.anotaaiapi.controller.product.dto;

import tech.wvs.anotaaiapi.domain.category.Category;

public record ProductResponse(String id,
                              String title,
                              String description,
                              String ownerId,
                              Integer price,
                              String categoryId) {
}
