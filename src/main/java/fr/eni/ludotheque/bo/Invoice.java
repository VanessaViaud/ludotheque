package fr.eni.ludotheque.bo;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "INVOICES")

public class Invoice {

    @EqualsAndHashCode.Exclude
    @Id
    @GeneratedValue
    private Integer id;

    private LocalDate paymentDate;

    @NonNull
    private Double amount;

    @OneToMany
    @JoinColumn(name = "invoice_id")
    private List<Rental> rentals;
}
