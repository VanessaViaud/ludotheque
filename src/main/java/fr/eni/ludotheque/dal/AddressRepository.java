package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
