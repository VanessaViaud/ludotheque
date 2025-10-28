package fr.eni.ludotheque.bo;

import lombok.*;

import java.util.UUID;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Client {

    @EqualsAndHashCode.Exclude private Integer clientNumber;
    @NonNull private String lastname;
    @NonNull private String firstname;
    @NonNull private String email;
    @NonNull private Address address;
    private String phoneNumber;
}
