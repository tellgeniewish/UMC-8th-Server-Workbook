package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Review;
import umc.spring.service.ReviewService.ReviewCommandService;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.web.dto.ReviewRequestDTO;
import umc.spring.web.dto.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/reviews")
@RequestMapping("/stores")
public class ReviewRestController {
    private final ReviewCommandService reviewCommandService;

////    @PostMapping("/")
//    @PostMapping("/{storeId}/reviews")
//    public ApiResponse<ReviewResponseDTO.AddReviewResultDTO> addReview(@PathVariable Long storeId, @RequestBody @Valid ReviewRequestDTO.AddReviewDto request){
//        Review review = reviewCommandService.addReview(storeId, request);
//        return ApiResponse.onSuccess(ReviewConverter.toAddReviewResultDTO(review));
////        return null
//    }

    @PostMapping(
            value = "/{storeId}/reviews",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}
    )
    public ApiResponse<ReviewResponseDTO.AddReviewResultDTO> createReview(
            @Parameter(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
            @RequestPart("request") @Valid ReviewRequestDTO.AddReviewDto request,

            @ExistStore @PathVariable Long storeId,
            @RequestParam Long memberId,
            @RequestPart(value = "reviewPicture", required = false) MultipartFile reviewPicture
    ) {
        Review review = reviewCommandService.createReview(memberId, storeId, request, reviewPicture);
        return ApiResponse.onSuccess(ReviewConverter.toAddReviewResultDTO(review));
    }

    @DeleteMapping("/reviews/image")
    public ApiResponse<String> deleteReviewImage(@RequestParam Long reviewId) {
        reviewCommandService.deleteReviewImage(reviewId);
        return ApiResponse.onSuccess("삭제 완료: " + reviewId);
    }
}