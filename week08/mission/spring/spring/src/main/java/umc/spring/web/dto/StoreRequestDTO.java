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
        @Min(0)
        @Max(100)
        Float score;
        @NotNull
        Long regionId;
    }
}
