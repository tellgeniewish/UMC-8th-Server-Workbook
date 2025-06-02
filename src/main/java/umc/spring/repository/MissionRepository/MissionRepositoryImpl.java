package umc.spring.repository.MissionRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import umc.spring.domain.Mission;
import umc.spring.domain.QMission;

import java.util.List;

import static umc.spring.domain.QStore.store;

@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Mission> showAllMissionsByRegionId(Long regionId, Pageable pageable) {
        QMission mission = QMission.mission;

        List<Mission> missions = queryFactory
                .selectFrom(mission)
                .join(mission.store, store).fetchJoin() // fetch join 추가
                .where(mission.store.region.id.eq(regionId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .selectFrom(mission)
                .where(mission.store.region.id.eq(regionId))
                .fetchCount();

//        return missions;
        return new PageImpl<>(missions, pageable, total);
    }
}
