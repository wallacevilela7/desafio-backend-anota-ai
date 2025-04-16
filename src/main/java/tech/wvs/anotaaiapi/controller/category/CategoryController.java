package tech.wvs.anotaaiapi.controller.category;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.wvs.anotaaiapi.controller.category.dto.CategoryRequest;
import tech.wvs.anotaaiapi.controller.category.dto.CategoryResponse;
import tech.wvs.anotaaiapi.controller.category.dto.CategoryUpdateRequest;
import tech.wvs.anotaaiapi.domain.category.CategoryMapper;
import tech.wvs.anotaaiapi.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest dto) {
        var entity = categoryService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoryMapper.toResponse(entity));
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findAll() {
        var content = categoryService
                .findAll()
                .stream()
                .map(CategoryMapper::toResponse)
                .toList();

        return ResponseEntity.ok(content);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable String id) {
        var entity = categoryService.findById(id);

        return entity.isPresent() ?
                ResponseEntity.ok(CategoryMapper.toResponse(entity.get())) :
                ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable String id,
                                       @RequestBody CategoryUpdateRequest dto) {
        var entity = categoryService.update(id, dto);

        return entity != null ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        var deleted = categoryService.delete(id);

        return deleted ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }
}
