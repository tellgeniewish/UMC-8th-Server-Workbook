package umc.spring.repository.MissionRepository;

import org.springframework.data.domain.Pageable;
import umc.spring.domain.Mission;
import org.springframework.data.domain.Page;
import java.util.List;

public interface MissionRepositoryCustom {
    Page<Mission> showAllMissionsByRegionId(Long regionId, Pageable pageable);
}
