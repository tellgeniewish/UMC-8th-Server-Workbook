package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MemberMissionService.MemberMissionCommandService;
import umc.spring.service.MemberMissionService.MemberMissionQueryService;
import umc.spring.validation.annotation.CheckChallenge;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.validation.annotation.ValidPage;
import umc.spring.web.dto.MemberMissionResponseDTO;
import umc.spring.web.dto.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/memberMissions")
public class MemberMissionRestController {
    private final MemberMissionCommandService memberMissionCommandService;
    private final MemberMissionQueryService memberMissionQueryService;

    @CheckChallenge
    @PostMapping("/challenge")
    public ApiResponse<String> reverseMission(@RequestParam Long memberId, @RequestParam Long missionId){
        memberMissionCommandService.reverseMission(memberId, missionId);
        return ApiResponse.onSuccess("가게의 미션을 도전 중인 미션에 추가했습니다!");
//        return null
    }

    @GetMapping("/{memberId}/missions/challenging")
    @Operation(summary = "내가 진행중인 미션 목록 조회 API", description = "내가 진행중인 미션 목록을 조회하는 API입니다. 페이징을 포함하니 query string으로 page 번호를 함께 보내주세요.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "멤버의 아이디. path variable입니다.")
    })
    public ApiResponse<MemberMissionResponseDTO.MemberMissionPreViewListDTO> getChallengingMissionList(@PathVariable(name = "memberId") Long memberId, @ValidPage @RequestParam(name = "page", defaultValue = "1") Integer page) {
        Page<MemberMission> memberMissionList = memberMissionQueryService.getMemberMissionList(memberId,page - 1);
        return ApiResponse.onSuccess(MemberMissionConverter.memberMissionPreViewListDTO(memberMissionList));
    }

    @PatchMapping("/complete")
    @Operation(summary = "진행중인 미션 진행 완료로 바꾸기 API", description = "진행중인 미션 진행 완료로 바꾸기 API입니다. 페이징을 포함하니 query string으로 page 번호를 함께 보내주세요.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    public ApiResponse<MemberMissionResponseDTO.MemberMissionPreViewDTO> completeMission(@RequestParam(name = "memberId") Long memberId, @RequestParam(name = "missionId") Long missionId) {
//        return ApiResponse.onSuccess(MemberMissionConverter.memberMissionPreViewListDTO(memberMissionCommandService.completeMission(memberId, missionId)));
        return ApiResponse.onSuccess(memberMissionCommandService.completeMission(memberId, missionId));
    }
}
