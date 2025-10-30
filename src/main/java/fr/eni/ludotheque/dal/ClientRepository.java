package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {

   // ci-dessous deux méthodes différentes
    @Query("select c from Client c where c.lastName like :firstCharacters")
    List<Client> findClientsByLastName(@Param("firstCharacters") String firstCharacters);

    // ou
    List<Client> findByLastNameIsStartingWith(String firstCharacters);

}
