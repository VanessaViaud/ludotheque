package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface GameRepository extends JpaRepository<Game,Integer> {


}
