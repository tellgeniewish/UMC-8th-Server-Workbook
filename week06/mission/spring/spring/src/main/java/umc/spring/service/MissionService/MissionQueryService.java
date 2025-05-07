package umc.spring.service.MissionService;


import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;

import java.util.List;

public interface MissionQueryService {
    List<Mission> findAllMissionsByRegionId(Long regionId);

    List<Mission> findAllMissionsByMemberIdAndMissionStatus(Long memberId, MissionStatus status);
}
