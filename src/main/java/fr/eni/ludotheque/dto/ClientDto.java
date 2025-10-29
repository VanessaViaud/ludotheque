package fr.eni.ludotheque.dto;

public record ClientDto(String lastName,
                        String firstName,
                        String email,
                        String phoneNumber,
                        String street,
                        String city,
                        String state,
                        String postalCode) {
}
