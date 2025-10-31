package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Game;

import java.util.List;

public interface GameService {

    Game addGame(Game game);

    List<Game> findAll();

    List<Object[]> findGamesWithCopiesCount();
}
