package fr.eni.ludotheque.dto;

import lombok.NonNull;

import java.time.LocalDate;

public record RentalDto(
        @NonNull
    LocalDate startDate,
        @NonNull
    Double pricePerDay,
        @NonNull
    String copieId,
        @NonNull
    String clientId
) {

}
