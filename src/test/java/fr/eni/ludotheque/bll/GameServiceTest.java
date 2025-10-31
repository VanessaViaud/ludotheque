package fr.eni.ludotheque.bll;

import fr.eni.ludotheque.bo.Category;
import fr.eni.ludotheque.bo.Game;
import fr.eni.ludotheque.dal.CategoryRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class GameServiceTest {

    @Autowired
    private GameService gameService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("Test d'ajout de jeux")
    @Transactional
    void testAddGame() {

        List<Category> categoriesTest = categoryRepository.findAll();
        categoriesTest.add(categoryRepository.findById(1).orElse(null));
        categoriesTest.add(categoryRepository.findById(2).orElse(null));
        Game gameTest = new Game("uno", "qjkfv14", 20.5);
        gameTest.setCategories(categoriesTest);
        gameService.addGame(gameTest);

    }

}
