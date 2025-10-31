package fr.eni.ludotheque.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClientDto(
        @NotBlank
        @Size(min = 1, max = 50)
        String lastName,
        @NotBlank
        @Size(min = 1, max = 50)
        String firstName,
        @Email
        String email,
        String phoneNumber,
        @NotBlank
        String street,
        @NotBlank
        String city,
        @NotBlank
        String postalCode
) {
}
