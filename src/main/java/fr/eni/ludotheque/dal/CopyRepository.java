package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Category;
import fr.eni.ludotheque.bo.Copy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CopyRepository extends JpaRepository<Copy, Integer> {
}
