package tech.wvs.anotaaiapi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import tech.wvs.anotaaiapi.domain.product.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
}
