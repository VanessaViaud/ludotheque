package fr.eni.ludotheque.bo;

import jakarta.persistence.*;
import lombok.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user_info")
public class UserInfo {
    @Id
    @GeneratedValue()
    @EqualsAndHashCode.Exclude
    private Integer id;

    @NonNull
    @Column(unique = true, length = 70)
    private String username;

    @NonNull
    @Column(length = 70)
    private String password;

    @NonNull
    @Column(length = 20)
    private String role;
}
