package umc.spring.repository.MemberRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import umc.spring.domain.Member;
import umc.spring.domain.QMember;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Member getMemberInfo(Long memberId) {
        QMember m = QMember.member;

        return queryFactory
                .selectFrom(m)
                .where(m.id.eq(memberId))
                .fetchOne();
    }
}
