package tech.wvs.anotaaiapi.service;

import org.springframework.stereotype.Service;
import tech.wvs.anotaaiapi.controller.product.dto.ProductRequest;
import tech.wvs.anotaaiapi.controller.product.dto.ProductUpdateRequest;
import tech.wvs.anotaaiapi.domain.product.Product;
import tech.wvs.anotaaiapi.domain.product.ProductMapper;
import tech.wvs.anotaaiapi.repositories.ProductRepository;
import tech.wvs.anotaaiapi.service.aws.AwsSnsService;
import tech.wvs.anotaaiapi.service.aws.MessageDto;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final AwsSnsService awsSnsService;

    public ProductService(ProductRepository productRepository, CategoryService categoryService, AwsSnsService awsSnsService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.awsSnsService = awsSnsService;
    }

    public Product create(ProductRequest dto) {
        var entity = ProductMapper.toEntity(dto);
        var category = categoryService.findById(dto.categoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        entity.setCategory(dto.categoryId());
        productRepository.save(entity);

        // Publish a message to the SNS topic
        this.awsSnsService.publish(new MessageDto(entity.getOwnerId()));

        return entity;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(String id) {
        return productRepository.findById(id);
    }

    public Product update(String id, ProductUpdateRequest dto) {
        var entity = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        updateFields(dto, entity);
        productRepository.save(entity);

        // Publish a message to the SNS topic
        this.awsSnsService.publish(new MessageDto(entity.getOwnerId()));

        return entity;
    }

    private void updateFields(ProductUpdateRequest dto, Product entity) {
        if (dto.title() != null && !dto.title().isBlank())
            entity.setTitle(dto.title());

        if (dto.description() != null && !dto.description().isBlank())
            entity.setDescription(dto.description());

        if (dto.price() != null)
            entity.setPrice(dto.price());

        if (dto.categoryId() != null && !dto.categoryId().isBlank()) {
            var category = categoryService.findById(dto.categoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));

            entity.setCategory(dto.categoryId());
        }
    }

    public boolean delete(String id) {
        var exists = productRepository.existsById(id);
        if (exists)
            productRepository.deleteById(id);

        return exists;
    }
}
