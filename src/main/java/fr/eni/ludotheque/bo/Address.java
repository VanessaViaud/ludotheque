package fr.eni.ludotheque.bo;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @EqualsAndHashCode.Exclude private Integer addressNumber;
    @NonNull private String city;
    @NonNull private String street;
    @NonNull private String postalCode;
}
