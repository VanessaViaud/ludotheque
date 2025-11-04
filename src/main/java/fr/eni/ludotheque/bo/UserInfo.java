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
@Document("user_info")
public class UserInfo {
    @Id
    private String id;

    @NonNull
    private String username;

    @NonNull
    private String password;

    @NonNull
    private String role;
}
