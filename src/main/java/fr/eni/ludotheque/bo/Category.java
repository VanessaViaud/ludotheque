package fr.eni.ludotheque.bo;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="CATEGORIES")
public class Category {

    @EqualsAndHashCode.Exclude
    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true,  nullable = false, length = 50)
    @NonNull
    private String label;

}
