package fr.eni.ludotheque.bo;

import jakarta.persistence.*;
import lombok.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ADDRESSES")
public class Address {

    @EqualsAndHashCode.Exclude
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, length = 100)
    @NonNull private String city;

    @Column(nullable = false, length = 100)
    @NonNull private String street;

    @Column(nullable = false, length = 10)
    @NonNull private String postalCode;
}
