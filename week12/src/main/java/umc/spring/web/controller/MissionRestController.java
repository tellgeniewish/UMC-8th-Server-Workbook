package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@Validated
//@RequestMapping("/missions")
@RequestMapping("/stores")
public class MissionRestController {
    private final MissionCommandService missionCommandService;

    //    @PostMapping("/")
    @PostMapping("/{storeId}/missions")
    public ApiResponse<MissionResponseDTO.AddMissionResultDTO> addMission(@PathVariable @ExistStore Long storeId, @RequestBody @Valid MissionRequestDTO.AddMissionDto request){
        Mission mission = missionCommandService.addMission(storeId, request);
        return ApiResponse.onSuccess(MissionConverter.toAddMissionResultDTO(mission));
//        return null
    }
}