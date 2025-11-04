package fr.eni.ludotheque.bo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Document("GAMES")
public class Game {

    @Id
    private String gameNumber;

    @NonNull private String title;

    @NonNull private String reference;

    private int minimalAge;

    private String description;

    private int durationInMinutes;

    @NonNull private double pricePerDay;

    @NonNull
    private List<Category> categories = new ArrayList<>();

    @NonNull
    private List<Copy> copies = new ArrayList<>();

}
