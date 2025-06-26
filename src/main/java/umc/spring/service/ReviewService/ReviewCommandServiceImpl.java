package umc.spring.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.expection.handler.MemberHandler;
import umc.spring.apiPayload.expection.handler.StoreHandler;
import umc.spring.aws.s3.AmazonS3Manager;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.Uuid;
import umc.spring.repository.MemberRepository.MemberRepository;
import umc.spring.repository.ReviewImageRepository.ReviewImageRepository;
import umc.spring.repository.ReviewRepository.ReviewRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.repository.UuidRepository.UuidRepository;
import umc.spring.web.dto.ReviewRequestDTO;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService{
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final AmazonS3Manager s3Manager;
    private final UuidRepository uuidRepository;
    private final ReviewImageRepository reviewImageRepository;

    @Override
    public Review addReview(Long storeId, ReviewRequestDTO.AddReviewDto request) {
        Member member =  memberRepository.findById(request.getMemberId()).orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
//        Store store = storeRepository.findById(request.getStoreId()).orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        Review newReview = ReviewConverter.toReview(request, store, member);

        return reviewRepository.save(newReview);
    }

    @Override
    public Review createReview(Long memberId, Long storeId, ReviewRequestDTO.AddReviewDto request, MultipartFile reviewPicture) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        Review review = ReviewConverter.toReview(request, store, member);

//        Review review = ReviewConverter.toReview(request);

        String uuid = UUID.randomUUID().toString();
        Uuid savedUuid = uuidRepository.save(Uuid.builder()
                .uuid(uuid).build());

        String pictureUrl = s3Manager.uploadFile(s3Manager.generateReviewKeyName(savedUuid), reviewPicture);

//        review.setMember(memberRepository.findById(memberId).get());
//        review.setStore(storeRepository.findById(storeId).get());

        reviewImageRepository.save(ReviewConverter.toReviewImage(pictureUrl, review));
        return reviewRepository.save(review);
    }
}
