package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
}
