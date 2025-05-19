package umc.spring.service.MemberMissionService;

import umc.spring.domain.mapping.MemberMission;

public interface MemberMissionCommandService {
    MemberMission reverseMission(Long memberId, Long missionId);
}
