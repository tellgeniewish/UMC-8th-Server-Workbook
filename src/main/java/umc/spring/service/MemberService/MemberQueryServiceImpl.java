package umc.spring.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.expection.handler.MemberHandler;
import umc.spring.config.security.jwt.JwtTokenProvider;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.repository.MemberRepository.MemberRepository;
import umc.spring.repository.ReviewRepository.ReviewRepository;
import umc.spring.web.dto.MemberResponseDTO;
import umc.spring.converter.MemberConverter;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    private final JwtTokenProvider jwtTokenProvider;

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

    @Override
    @Transactional(readOnly = true)
    public MemberResponseDTO.MemberInfoDTO getMemberInfo(HttpServletRequest request){
        Authentication authentication = jwtTokenProvider.extractAuthentication(request); // 토큰을 파싱하고, Authentication 객체를 추출
        String email = authentication.getName(); // 추출해낸 인증 객체(Authentication)을 통해 사용자 정보를 가져온다.

        Member member = memberRepository.findByEmail(email) // MemberRepository 로부터 사용자 정보를 조회
                .orElseThrow(()-> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        return MemberConverter.toMemberInfoDTO(member); // 정보 조회에 성공하면, 우리가 정의한 Response DTO인 MemberInfoDTO 로 반환
    }
}
