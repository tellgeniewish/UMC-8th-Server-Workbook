package umc.spring.repository.MemberMissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;

import java.util.List;

public interface MemberMissionRepositoryCustom {
    Page<Mission> showAllMissionsByMemberIdAndStatus(Long memberId, MissionStatus status, Pageable pageable);
}
