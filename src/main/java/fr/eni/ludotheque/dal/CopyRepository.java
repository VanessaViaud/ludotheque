package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Copy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CopyRepository extends JpaRepository<Copy, Integer> {

    @Query("select distinct c.game, count(c) from Copy c where c.available = true group by c.game")
    List<Object[]> findAllByAvailableAndNumberOfCopies();
 }
