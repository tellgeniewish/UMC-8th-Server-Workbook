package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Review;
import umc.spring.service.ReviewService.ReviewCommandService;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/reviews")
@RequestMapping("/stores")
public class ReviewRestController {
    private final ReviewCommandService reviewCommandService;

//    @PostMapping("/")
    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResponseDTO.AddReviewResultDTO> addReview(@PathVariable Long storeId, @RequestBody @Valid ReviewRequestDTO.AddReviewDto request){
        Review review = reviewCommandService.addReview(storeId, request);
        return ApiResponse.onSuccess(ReviewConverter.toAddReviewResultDTO(review));
//        return null
    }
}