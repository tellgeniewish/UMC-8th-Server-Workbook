package umc.spring.web.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

public class ReviewRequestDTO {
    @Getter
    public static class AddReviewDto{
        @Size(max = 500)
        String content;
        @NotNull
        @DecimalMin(value = "0.0", inclusive = true)
        @DecimalMax(value = "5.0", inclusive = true)
        Float score;
        @ExistStore
        @NotNull
        Long storeId;
        @NotNull
        Long memberId;
    }
}
