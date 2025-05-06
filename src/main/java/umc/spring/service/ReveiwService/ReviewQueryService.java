package umc.spring.service.ReveiwService;

import umc.spring.domain.Review;

public interface ReviewQueryService {
    //List<Review> findReviewsByStore(Long storeId);
    //Review findReviewById(Long reviewId);
    Review addReview(String content, Float rating, Long memberId, Long storeId);
}