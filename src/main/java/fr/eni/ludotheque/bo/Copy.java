package fr.eni.ludotheque.bo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Copy {

    @Id
    private String _id;
    @NonNull
    private String barcode;
    private Boolean available = true;

}
