package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<Address, String> {
}
