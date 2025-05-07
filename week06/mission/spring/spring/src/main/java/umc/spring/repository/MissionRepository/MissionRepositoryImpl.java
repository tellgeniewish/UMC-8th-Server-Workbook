package umc.spring.repository.MissionRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import umc.spring.domain.Mission;
import umc.spring.domain.QMission;

import java.util.List;

import static umc.spring.domain.QStore.store;

@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Mission> showAllMissionsByRegionId(Long regionId) {
        QMission mission = QMission.mission;

        List<Mission> missions = queryFactory
                .selectFrom(mission)
                .join(mission.store, store).fetchJoin() // fetch join 추가
                .where(mission.store.region.id.eq(regionId))
                .fetch();

        return missions;
    }
}
