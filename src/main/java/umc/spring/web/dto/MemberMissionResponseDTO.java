package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.domain.enums.MissionStatus;

import java.time.LocalDate;
import java.util.List;

public class MemberMissionResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberMissionPreViewListDTO {
        List<MemberMissionResponseDTO.MemberMissionPreViewDTO> memberMissionList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberMissionPreViewDTO {
        Long missionId;
        String missionSpec;
        Integer reward;
        LocalDate deadline;
        String storeName; // 미션에 연결된 가게 이름
        MissionStatus status; // 이 멤버가 이 미션에 대해 어떤 상태인지
    }
}
