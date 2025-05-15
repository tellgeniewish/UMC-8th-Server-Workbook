package umc.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.enums.MemberStatus;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.enums.SocialType;
import umc.spring.repository.MemberRepository.MemberRepository;
import umc.spring.service.MemberService.MemberQueryService;
import umc.spring.service.ReveiwService.ReviewQueryService;
import umc.spring.service.StoreService.StoreQueryService;
import umc.spring.service.MissionService.MissionQueryService;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner run(ApplicationContext context) {
		return args -> {
			StoreQueryService storeService = context.getBean(StoreQueryService.class);

			// 파라미터 값 설정
			String name = "요아정";
			Float score = 4.0f;

			// 쿼리 메서드 호출 및 쿼리 문자열과 파라미터 출력
			System.out.println("Executing findStoresByNameAndScore with parameters:");
			System.out.println("Name: " + name);
			System.out.println("Score: " + score);

//			storeService.findStoresByNameAndScore(name, score)
//					.forEach(System.out::println);

			MemberRepository memberRepository = context.getBean(MemberRepository.class);

			Member member = Member.builder()
					.name("홍길동")
					.age(20)
					.address("서울시 마포구")
					.specAddress("연남동 123-4")
					.gender(Gender.MALE)
					.socialType(SocialType.KAKAO)
					.status(MemberStatus.ACTIVE)
					.email("hong@example.com")
					.point(100)
					.build();

			memberRepository.save(member); // DB에 저장됨

			 //하드코딩
			long memberId = 1L; // L은 리터럴로, long 타입임을 명시하는 접미사

			// 리뷰 추가 확인
			ReviewQueryService ReviewService = context.getBean(ReviewQueryService.class);
			Review egReview = ReviewService.addReview("짱이네요!", 4.5F, memberId, 1L);
//			System.out.println(egReview);

			// 하드코딩
			Long regionId = 1L;
			MissionStatus status = MissionStatus.CHALLENGING;

			MissionQueryService MissionService = context.getBean(MissionQueryService.class);
			// 지역별 미션 확인
			System.out.println("Missions Test regionId " + regionId + ":");
			PageRequest pageRequest = PageRequest.of(0, 5); // 0페이지, 최대 5개씩
//			MissionService.findAllMissionsByRegionId(regionId)
//					.forEach(System.out::println);
			Page<Mission> page = MissionService.findAllMissionsByRegionId(regionId, pageRequest);
			page.getContent().forEach(System.out::println);

			// 멤버 아이디랑 미션 상태로 미션 확인
//			MissionService.findAllMissionsByMemberIdAndMissionStatus(memberId, status)
//					.forEach(System.out::println);
			Page<Mission> missionPage = MissionService.findAllMissionsByMemberIdAndMissionStatus(memberId, status, PageRequest.of(0, 5));
			missionPage.getContent().forEach(System.out::println);

			MemberQueryService MemberService = context.getBean(MemberQueryService.class);
			Member myPageInfo = MemberService.getMyPageInfo(memberId);
			System.out.println("myPageInfo: " + myPageInfo);
		};
	}
}
