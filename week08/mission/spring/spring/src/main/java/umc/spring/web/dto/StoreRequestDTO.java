package umc.spring.web.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

public class StoreRequestDTO {
    @Getter
    public static class AddStoreDto{
        @NotBlank
        String name;
        @Size(min = 5, max = 12)
        String address;
        @DecimalMin(value = "0.0", inclusive = true)
        @DecimalMax(value = "100.0", inclusive = true)
        Float score;
        @NotNull
        Long regionId;
    }
}
