package tech.wvs.anotaaiapi.controller.product.dto;

public record ProductUpdateRequest(String title,
                                   String description,
                                   Integer price,
                                   String categoryId) {
}
