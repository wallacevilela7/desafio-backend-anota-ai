package tech.wvs.anotaaiapi.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import tech.wvs.anotaaiapi.domain.category.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {
}
