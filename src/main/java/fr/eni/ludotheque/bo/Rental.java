package fr.eni.ludotheque.bo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "RENTALS")
public class Rental {

    @EqualsAndHashCode.Exclude
    @Id
    @GeneratedValue
    private Integer rentalNumber;

    @NonNull
    private LocalDate startDate;
    private LocalDate endDate;
    private Double pricePerDay;

    @ManyToOne
    @NonNull
    @JoinColumn(name = "copy_id")
    private Copy copy;

    @ManyToOne
    @NonNull
    @JoinColumn(name = "client_id")
    private Client client;

}
