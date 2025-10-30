package fr.eni.ludotheque.dal;

import java.util.List;
import fr.eni.ludotheque.bo.Copy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CopyRepository extends JpaRepository<Copy, Integer> {

    @Query("select distinct c.game, count(c) from Copy c where c.rentable = true group by c.game")
    List<Object[]> findAllByRentableAndNumberOfCopies();
 }
