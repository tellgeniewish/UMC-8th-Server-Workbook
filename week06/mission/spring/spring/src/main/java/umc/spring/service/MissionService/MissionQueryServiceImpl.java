package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.repository.MissionRepository.MissionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {
    private final MissionRepository missionRepository;
    //private final MemberMissionRepository memberMissionRepository;

    @Override
    public List<Mission> findAllMissionsByRegionId(Long regionId) {
        return missionRepository.showAllMissionsByRegionId(regionId);
    }
    /*
    @Override
    public List<Mission> findAllMissionsByMemberIdAndMissionStatus(Long memberId, MissionStatus status) {
        return memberMissionRepository.showAllMissionsByMemberIdAndStatus(memberId, status);
    }

     */
}
