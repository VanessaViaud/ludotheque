package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental,Integer> {

}
