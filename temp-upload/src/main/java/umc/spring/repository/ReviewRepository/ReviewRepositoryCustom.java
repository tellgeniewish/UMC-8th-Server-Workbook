package umc.spring.repository.ReviewRepository;

import umc.spring.domain.Review;

public interface ReviewRepositoryCustom {
    Review createReview(String content, Float rating, Long memberId, Long storeId);
}
