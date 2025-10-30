package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Category;
import fr.eni.ludotheque.bo.Game;
import fr.eni.ludotheque.dal.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class GameServiceTest {

    @Autowired
    private GameService gameService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("Test d'ajout de jeux")
    void testAddGame() {

        Optional<Category> category = categoryRepository.findById(1);
        Game gameTest = new Game("uno", "qjkfv14", 20.5);
        gameService.addGame(gameTest);

    }

}
