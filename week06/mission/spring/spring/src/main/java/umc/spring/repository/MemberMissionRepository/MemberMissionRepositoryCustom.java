package umc.spring.repository.MemberMissionRepository;

import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;

import java.util.List;

public interface MemberMissionRepositoryCustom {
    List<Mission> showAllMissionsByMemberIdAndStatus(Long memberId, MissionStatus status);
}
