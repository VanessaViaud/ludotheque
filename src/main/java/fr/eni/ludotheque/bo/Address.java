package fr.eni.ludotheque.bo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Address {

    //private String id;
    @NonNull private String city;
    @NonNull private String street;
    @NonNull private String postalCode;
}
