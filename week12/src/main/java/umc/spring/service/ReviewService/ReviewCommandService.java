package umc.spring.service.ReviewService;

import org.springframework.web.multipart.MultipartFile;
import umc.spring.domain.Review;
import umc.spring.web.dto.ReviewRequestDTO;

public interface ReviewCommandService {
    Review addReview(Long storeId, ReviewRequestDTO.AddReviewDto request);
    Review createReview(Long memberId, Long storeId, ReviewRequestDTO.AddReviewDto request, MultipartFile reviewPicture);
    void deleteReviewImage(Long reviewId);
}
