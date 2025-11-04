package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Rental;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RentalRepository extends MongoRepository<Rental,String> {

}
