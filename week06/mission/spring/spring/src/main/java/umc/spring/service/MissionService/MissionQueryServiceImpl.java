package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.repository.MemberMissionRepository.MemberMissionRepository;
import umc.spring.repository.MissionRepository.MissionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public Page<Mission> findAllMissionsByRegionId(Long regionId, Pageable pageable) {
        return missionRepository.showAllMissionsByRegionId(regionId, pageable);
    }

    @Override
    public Page<Mission> findAllMissionsByMemberIdAndMissionStatus(Long memberId, MissionStatus status, Pageable pageable) {
        return memberMissionRepository.showAllMissionsByMemberIdAndStatus(memberId, status, pageable);
    }
}
