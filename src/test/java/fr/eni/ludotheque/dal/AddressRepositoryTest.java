package fr.eni.ludotheque.dal;

import fr.eni.ludotheque.bo.Address;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Test
    @DisplayName("Test d'ajout d'un client en DB - cas droit")
    void addAddress() {

        Address address = new Address();
        Address address2 = new Address("PARIS", "rue de la Paix", "75000");
        address.setCity("New York");
        addressRepository.save(address);
        addressRepository.save(address2);

        assertNotEquals("New York", address2.getCity());
        Optional<Address> addressOptional1 = Optional.ofNullable(addressRepository.findAddressesByCityContaining("ne"));
        //Optional<Address> addressOptional = addressRepository.findById(address2.getId());
        //assertTrue(addressOptional.isPresent());
        //assertEquals(address2.getCity(), addressOptional.get().getCity());

    }
}
