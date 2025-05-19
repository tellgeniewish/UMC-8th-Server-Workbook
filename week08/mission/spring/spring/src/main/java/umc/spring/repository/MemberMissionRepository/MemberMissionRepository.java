package umc.spring.repository.MemberMissionRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long>, MemberMissionRepositoryCustom {
    boolean existsByMemberIdAndMissionIdAndStatus(Long memberId, Long missionId, MissionStatus status);
}
