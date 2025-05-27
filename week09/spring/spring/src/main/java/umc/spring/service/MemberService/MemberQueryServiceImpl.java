package umc.spring.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.expection.handler.MemberHandler;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.repository.MemberRepository.MemberRepository;
import umc.spring.repository.ReviewRepository.ReviewRepository;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public Member getMyPageInfo(Long memberId) {
        return memberRepository.getMemberInfo(memberId);
    }

    @Override
    public Page<Review> getMemberReviewList(Long MemberId, Integer page) {
        Member member =  memberRepository.findById(MemberId).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));


        Page<Review> MemberPage = reviewRepository.findAllByMember(member, PageRequest.of(page, 10));
        return MemberPage;
//        return null;
    }
}
