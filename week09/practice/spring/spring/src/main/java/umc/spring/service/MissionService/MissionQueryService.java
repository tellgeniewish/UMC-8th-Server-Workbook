package umc.spring.service.MissionService;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;

import java.util.List;

public interface MissionQueryService {
    Page<Mission> findAllMissionsByRegionId(Long regionId, Pageable pageable);

    Page<Mission> findAllMissionsByMemberIdAndMissionStatus(Long memberId, MissionStatus status, Pageable pageable);
}
