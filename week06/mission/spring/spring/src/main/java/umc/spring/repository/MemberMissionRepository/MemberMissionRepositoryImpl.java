package umc.spring.repository.MemberMissionRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.QMemberMission;

import java.util.List;

@RequiredArgsConstructor
public class MemberMissionRepositoryImpl implements MemberMissionRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Mission> showAllMissionsByMemberIdAndStatus(Long memberId, MissionStatus status) {
        QMemberMission mm = QMemberMission.memberMission;
        return queryFactory
                .select(mm.mission)
                .from(mm)
                .where(mm.member.id.eq(memberId).and(mm.status.eq(status)))
                .fetch();
    }
}
