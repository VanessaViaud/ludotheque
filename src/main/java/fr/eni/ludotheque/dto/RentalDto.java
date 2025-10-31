package fr.eni.ludotheque.dto;

import java.time.LocalDateTime;

public record RentalDto(
    LocalDateTime startDate,
    Double pricePerDay,
    Integer copieId,
    Boolean available,
    Integer clientId
) {

}
