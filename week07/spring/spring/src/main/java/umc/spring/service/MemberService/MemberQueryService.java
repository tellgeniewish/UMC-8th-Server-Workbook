package umc.spring.service.MemberService;

import umc.spring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberQueryService {
    Member getMyPageInfo(Long memberId);
}
