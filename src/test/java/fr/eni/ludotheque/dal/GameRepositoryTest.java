package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Category;
import fr.eni.ludotheque.bo.Game;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GameRepositoryTest {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("Test findAll")
    @Transactional
    public void addGame() {

        Category category = new Category("cards");
        List<Category> categories = new ArrayList<>();
        categories.add(category);
        categoryRepository.save(category);

        assertNotNull(categories);

        categoryRepository.flush();
        Game game = new Game("Uno", "gf1h2sb", 5.5);
        game.setDescription("jeu de carte dans lequel le vainqueur est le premier joueur à se défausser de la dernière carte de sa main");
        game.setCategories(categories);
        Game newGame = gameRepository.save(game);

        assertNotNull(newGame);
        assertNotNull(newGame.getGameNumber());
        assertEquals(categories, newGame.getCategories());

        gameRepository.flush();
        Optional<Game> gameOptional = gameRepository.findById(game.getGameNumber());
        assertTrue(gameOptional.isPresent());
    }
}
