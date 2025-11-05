package fr.eni.ludotheque.bo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@NoArgsConstructor

public class Invoice {

    @Id
    private String _id;
    private LocalDate paymentDate;

    @NonNull
    private Double amount;
    @NonNull
    private List<Rental> rentals =  new ArrayList<>();
}
