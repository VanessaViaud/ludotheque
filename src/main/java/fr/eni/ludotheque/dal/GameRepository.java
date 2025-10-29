package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game,Integer> {
}
