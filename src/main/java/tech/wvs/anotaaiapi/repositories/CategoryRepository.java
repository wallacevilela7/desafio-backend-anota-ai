package tech.wvs.anotaaiapi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import tech.wvs.anotaaiapi.domain.category.Category;
import tech.wvs.anotaaiapi.domain.product.Product;

import java.util.Optional;

public interface CategoryRepository extends MongoRepository<Category, String> {
    Optional<Product> findByTitle(String title);
}
