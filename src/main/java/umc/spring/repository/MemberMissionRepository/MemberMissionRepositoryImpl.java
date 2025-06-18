package umc.spring.repository.MemberMissionRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.QMemberMission;

import java.util.List;

@RequiredArgsConstructor
public class MemberMissionRepositoryImpl implements MemberMissionRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Mission> showAllMissionsByMemberIdAndStatus(Long memberId, MissionStatus status, Pageable pageable) {
        QMemberMission mm = QMemberMission.memberMission;

        // 전체 개수
        long total = queryFactory
                .select(mm.count())
                .from(mm)
                .where(mm.member.id.eq(memberId).and(mm.status.eq(status)))
                .fetchOne();

        // 페이징 적용된 조회
        List<Mission> content = queryFactory
                .select(mm.mission)
                .from(mm)
                .where(mm.member.id.eq(memberId).and(mm.status.eq(status)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(content, pageable, total);
    }
}
