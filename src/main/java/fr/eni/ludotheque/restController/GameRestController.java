package fr.eni.ludotheque.restController;

import fr.eni.ludotheque.bll.GameService;
import fr.eni.ludotheque.bo.Game;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameRestController {

    @NonNull
    private GameService gameService;

    public GameRestController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/games")
    public ResponseEntity<List<Game>> findAll() {
        List<Game> games = gameService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(games);
    }

    @GetMapping("/games/available")
    public ResponseEntity<List<Object[]>> findAllAvailable() {
        List<Object[]> gamesAndAvailables = gameService.findGamesWithCopiesCount();
        return ResponseEntity.status(HttpStatus.OK).body(gamesAndAvailables);
    }
}
