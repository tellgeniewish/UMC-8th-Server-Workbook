package umc.spring.web.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import umc.spring.validation.annotation.ExistStore;

import java.time.LocalDate;

public class MissionRequestDTO {
    @Getter
    public static class AddMissionDto{
        @Positive
        @Min(value = 1)
        private Integer reward;

        @NotNull
        private LocalDate deadline;

        @NotBlank
        private String missionSpec;

//        @NotNull
//        @ExistStore
//        private Long storeId;

//        @NotNull
//        Long memberId;
    }
}
