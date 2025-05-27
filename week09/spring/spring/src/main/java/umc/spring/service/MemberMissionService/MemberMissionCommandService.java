package umc.spring.service.MemberMissionService;

import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MemberMissionResponseDTO;

public interface MemberMissionCommandService {
    MemberMission reverseMission(Long memberId, Long missionId);
    MemberMissionResponseDTO.MemberMissionPreViewDTO completeMission(Long memberId, Long missionId);
}
