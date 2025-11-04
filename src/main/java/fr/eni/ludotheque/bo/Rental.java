package fr.eni.ludotheque.bo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Document("RENTALS")
public class Rental {

    @Id
    private String rentalNumber;

    @NonNull
    private LocalDate startDate;
    private LocalDate endDate;
    private Double pricePerDay;
    private Copy copy;
    private Client client;

}
