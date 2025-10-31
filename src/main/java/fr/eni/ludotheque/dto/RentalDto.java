package fr.eni.ludotheque.dto;

import lombok.NonNull;

import java.time.LocalDateTime;

public record RentalDto(
        @NonNull
    LocalDateTime startDate,
        @NonNull
    Double pricePerDay,
        @NonNull
    Integer copieId,
        @NonNull
    Boolean available,
        @NonNull
    Integer clientId
) {

}
