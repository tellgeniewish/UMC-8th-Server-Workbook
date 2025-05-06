package umc.spring.service.ReveiwService;

import umc.spring.domain.Review;

import java.util.List;

public interface ReviewQueryService {
    List<Review> findReviewsByStore(Long storeId);
    Review findReviewById(Long reviewId);
}
