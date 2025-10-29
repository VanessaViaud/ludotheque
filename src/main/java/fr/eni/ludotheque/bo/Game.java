package fr.eni.ludotheque.bo;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="GAMES")
public class Game {

    @EqualsAndHashCode.Exclude
    @Id
    @GeneratedValue
    private Integer gameNumber;

    @Column(nullable = false, length = 100)
    @NonNull private String title;

    @Column(unique = true, nullable = false, length = 50)
    @NonNull private String reference;

    @Column(nullable = false, length = 2)
    private int minimalAge;

    @Column(length = 350)
    private String description;

    @Column(length = 3)
    private int durationInMinutes;

    @Column(nullable = false, length = 6)
    @NonNull private double pricePerDay;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name="GAMES_CATEGORIES",
                joinColumns = {@JoinColumn(name="game_id")},
            inverseJoinColumns = {@JoinColumn(name="category_id")}
    )
    @NonNull
    private List<Category> categories = new ArrayList<>();

}
