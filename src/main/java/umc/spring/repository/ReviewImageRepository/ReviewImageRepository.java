package umc.spring.repository.ReviewImageRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.ReviewImage;

import java.util.Optional;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {
    Optional<ReviewImage> findByReviewId(Long reviewId);
}
