package tech.wvs.anotaaiapi.controller.product.dto;

public record ProductRequest(String title,
                             String description,
                             String ownerId,
                             Integer price,
                             String categoryId) {
}
