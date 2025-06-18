package umc.spring.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.web.dto.MemberResponseDTO;

import java.util.List;
import java.util.Optional;

public interface MemberQueryService {
    Member getMyPageInfo(Long memberId);
    Page<Review> getMemberReviewList(Long MemberId, Integer page);

    MemberResponseDTO.MemberInfoDTO getMemberInfo(HttpServletRequest request);
}
