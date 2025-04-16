package tech.wvs.anotaaiapi.controller.product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.wvs.anotaaiapi.controller.product.dto.ProductRequest;
import tech.wvs.anotaaiapi.controller.product.dto.ProductResponse;
import tech.wvs.anotaaiapi.controller.product.dto.ProductUpdateRequest;
import tech.wvs.anotaaiapi.domain.product.ProductMapper;
import tech.wvs.anotaaiapi.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest dto) {
        var entity = productService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductMapper.toResponse(entity));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        var content = productService
                .findAll()
                .stream()
                .map(ProductMapper::toResponse)
                .toList();

        return ResponseEntity.ok(content);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable String id) {
        var entity = productService.findById(id);

        return entity.isPresent() ?
                ResponseEntity.ok(ProductMapper.toResponse(entity.get())) :
                ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable String id,
                                       @RequestBody ProductUpdateRequest dto) {
        var entity = productService.update(id, dto);

        return entity != null ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        var deleted = productService.delete(id);

        return deleted ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }
}
