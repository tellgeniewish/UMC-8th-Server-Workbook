package umc.spring.repository.ReviewRepository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.repository.MemberRepository.MemberRepository;
import umc.spring.repository.StoreRepository.StoreRepository;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {
    private final EntityManager entityManager;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    //private final ReviewRepository reviewRepository;

    @Override
    public Review createReview(String content, Float rating, Long memberId, Long storeId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Store not found"));

        Review review = Review.builder()
                //return Review.builder()
                .content(content)
                .score(rating)
                .member(member)
                .store(store)
                .build();

        //return reviewRepository.save(review);
        entityManager.persist(review); // 객체를 DB에 저장

        return review; // Review 반환
    }
}
