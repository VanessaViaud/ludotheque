package fr.eni.ludotheque.restController;

import fr.eni.ludotheque.bll.GameService;
import fr.eni.ludotheque.bo.Game;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameRestController {

    private final GameService gameService;

    public GameRestController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/games")
    public ResponseEntity<ApiResponse<List<Game>>> findAll() {
        List<Game> games = gameService.findAll();
        return ResponseEntity.ok(new ApiResponse<>(true, "ok", games));
    }

    @GetMapping("/games/available")
    public ResponseEntity<ApiResponse<List<Object[]>>> findAllAvailable() {
        List<Object[]> gamesAndAvailable = gameService.findGamesWithCopiesCount();
        return ResponseEntity.ok(new ApiResponse<>(true, "ok", gamesAndAvailable));
    }
}
