package umc.spring.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Review;
import umc.spring.repository.ReviewRepository.ReviewRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewQueryServiceImpl implements ReviewQueryService{
    private final ReviewRepository reviewRepository; // 의존성 주입

    /*
    @Override
    public List<Review> findReviewsByStore(Long storeId) {

    }

    @Override
    public Review findReviewById(Long reviewId) {

    }
    */

    @Override
    public Review addReview(String content, Float rating, Long memberId, Long storeId) {
        return reviewRepository.createReview(content, rating, memberId, storeId);

        //Review review = reviewRepository.createReview(content, rating, memberId, storeId);
        //return reviewRepository.save(review);
    }
}
