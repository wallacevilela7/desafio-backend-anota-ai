package tech.wvs.anotaaiapi.controller.product.dto;

import tech.wvs.anotaaiapi.domain.category.Category;

public record ProductUpdateRequest(String title,
                                   String description,
                                   Integer price,
                                   String category) {
}
