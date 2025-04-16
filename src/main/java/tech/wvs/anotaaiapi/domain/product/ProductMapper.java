package tech.wvs.anotaaiapi.domain.product;

import tech.wvs.anotaaiapi.controller.product.dto.ProductRequest;
import tech.wvs.anotaaiapi.controller.product.dto.ProductResponse;

public class ProductMapper {

    public static Product toEntity(ProductRequest productRequest) {
        var product = new Product();
        product.setTitle(productRequest.title());
        product.setDescription(productRequest.description());
        product.setOwnerId(productRequest.ownerId());
        product.setPrice(productRequest.price());
        return product;
    }

    public static ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getOwnerId(),
                product.getPrice(),
                product.getCategory()
        );
    }
}
