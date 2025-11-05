package fr.eni.ludotheque.bo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Document("CLIENTS")
public class Client {

    @Id
    private String _id;

    @NonNull
    private String lastName;

    @NonNull
    private String firstName;

    @NonNull
    private String email;

    @NonNull
    private Address address;

    private String phoneNumber;
}
