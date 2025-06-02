package umc.spring.service.MemberService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.Review;

import java.util.List;
import java.util.Optional;

public interface MemberQueryService {
    Member getMyPageInfo(Long memberId);
    Page<Review> getMemberReviewList(Long MemberId, Integer page);
}
