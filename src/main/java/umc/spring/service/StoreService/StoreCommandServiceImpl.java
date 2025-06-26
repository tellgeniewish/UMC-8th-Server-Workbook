package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.expection.handler.RegionHandler;
import umc.spring.aws.s3.AmazonS3Manager;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Region;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.repository.MemberRepository.MemberRepository;
import umc.spring.repository.RegionRepository.RegionRepository;
import umc.spring.repository.ReviewRepository.ReviewRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.web.dto.StoreRequestDTO;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {
    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Override
    public Store addStore(StoreRequestDTO.AddStoreDto request) {
        Region region =  regionRepository.findById(request.getRegionId()).orElseThrow(() -> new RegionHandler(ErrorStatus.REGION_NOT_FOUND));

        Store newStore = StoreConverter.toStore(request, region);

        return storeRepository.save(newStore);
    }
}
