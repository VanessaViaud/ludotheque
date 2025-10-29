package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
