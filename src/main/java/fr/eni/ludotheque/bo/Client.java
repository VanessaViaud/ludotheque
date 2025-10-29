package fr.eni.ludotheque.bo;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CLIENTS")
public class Client {

    @EqualsAndHashCode.Exclude
    @Id
    @GeneratedValue
    private Integer clientNumber;

    @Column(nullable = false, length = 100)
    @NonNull private String lastname;

    @Column(nullable = false, length = 100)
    @NonNull private String firstname;

    @Column(nullable = false, length = 100, unique = true)
    @NonNull private String email;

    @OneToOne(cascade = CascadeType.PERSIST)
    @NonNull private Address address;

    @Column(length = 15)
    private String phoneNumber;
}
