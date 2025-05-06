package umc.spring.service.ReveiwService;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.repository.ReviewRepository.ReviewRepository;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl {
    private final ReviewRepository reviewRepository; // 의존성 주입
}
