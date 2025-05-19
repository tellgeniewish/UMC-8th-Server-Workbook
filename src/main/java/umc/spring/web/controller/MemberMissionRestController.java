package umc.spring.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.service.MemberMissionService.MemberMissionCommandService;
import umc.spring.validation.annotation.CheckChallenge;

@RestController
@RequiredArgsConstructor
@RequestMapping("/memberMissions")
public class MemberMissionRestController {
    private final MemberMissionCommandService memberMissionCommandService;

    @CheckChallenge
    @PostMapping("/challenge")
    public ApiResponse<String> reverseMission(@RequestParam Long memberId, @RequestParam Long missionId){
        memberMissionCommandService.reverseMission(memberId, missionId);
        return ApiResponse.onSuccess("가게의 미션을 도전 중인 미션에 추가했습니다!");
//        return null
    }
}
