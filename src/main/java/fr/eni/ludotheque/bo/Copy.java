package fr.eni.ludotheque.bo;

import jakarta.persistence.*;
import lombok.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="COPIES")
public class Copy {

    @EqualsAndHashCode.Exclude
    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true,  nullable = false, length = 50)
    @NonNull
    private String barcode;

    @Basic(optional = false)
    private Boolean available = true;

    @NonNull
    @ManyToOne
    @JoinColumn(name="game_id")
    private Game game;
}
