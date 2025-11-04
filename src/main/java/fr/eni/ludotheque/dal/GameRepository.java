package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameRepository extends MongoRepository<Game,String> {

  //  @Query("select distinct c.game, count(c) from Copy c where c.available = true group by c.game")
   // List<Object[]> findAllByAvailableAndNumberOfCopies();

}
