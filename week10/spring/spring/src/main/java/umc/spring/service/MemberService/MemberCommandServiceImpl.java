package umc.spring.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.expection.handler.FoodCategoryHandler;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MemberPreferConverter;
import umc.spring.domain.FoodCategory;
import umc.spring.domain.Member;
import umc.spring.domain.mapping.MemberPrefer;
import umc.spring.repository.FoodCategoryRepository.FoodCategoryRepository;
import umc.spring.repository.MemberRepository.MemberRepository;
import umc.spring.web.dto.MemberRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDto request) {

        Member newMember = MemberConverter.toMember(request);
        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

        memberPreferList.forEach(memberPrefer -> {memberPrefer.setMember(newMember);});

        newMember.encodePassword(passwordEncoder.encode(request.getPassword()));
        // `PasswordEncoder`를 사용한 비밀번호 암호화 사용 이유
        // 1. 보안 강화: 평문 비밀번호를 데이터베이스에 저장하지 않아 보안을 강화
        // 2. 단방향 해시: `BCryptPasswordEncoder`는 단방향 해시 함수를 사용하여 원본 비밀번호를 복원할 수 없게 만듦
        // 3. 솔트(Salt) 사용: BCrypt는 자동으로 솔트를 생성하여 레인보우 테이블 공격을 방지

        return memberRepository.save(newMember);
    }
}
