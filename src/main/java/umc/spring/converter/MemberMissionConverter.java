package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MemberMissionResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class MemberMissionConverter {
    public static MemberMission toReverseMission(Member member, Mission mission) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.CHALLENGING)  // 상태는 도전 중
                .build();
    }

    public static MemberMissionResponseDTO.MemberMissionPreViewDTO memberMissionPreViewDTO(MemberMission memberMission){
        return MemberMissionResponseDTO.MemberMissionPreViewDTO.builder()
                .missionId(memberMission.getMission().getId()) // 미션의 id
                .missionSpec(memberMission.getMission().getMissionSpec())
                .reward(memberMission.getMission().getReward())
                .deadline(memberMission.getMission().getDeadline())
                .storeName(memberMission.getMission().getStore().getName())
                .status(memberMission.getStatus())
                .build();
//        return null;
    }

    public static MemberMissionResponseDTO.MemberMissionPreViewListDTO memberMissionPreViewListDTO(Page<MemberMission> memberMissionList){
        List<MemberMissionResponseDTO.MemberMissionPreViewDTO> memberMissionPreViewDTOList = memberMissionList.stream()
                .map(MemberMissionConverter::memberMissionPreViewDTO).collect(Collectors.toList());

        return MemberMissionResponseDTO.MemberMissionPreViewListDTO.builder()
                .isLast(memberMissionList.isLast())
                .isFirst(memberMissionList.isFirst())
                .totalPage(memberMissionList.getTotalPages())
                .totalElements(memberMissionList.getTotalElements())
                .listSize(memberMissionPreViewDTOList.size())
                .memberMissionList(memberMissionPreViewDTOList)
                .build();
//        return null;
    }

    public static MemberMissionResponseDTO.MemberMissionPreViewDTO toCompleteResultDTO(MemberMission memberMission) {
        return MemberMissionResponseDTO.MemberMissionPreViewDTO.builder()
                .missionId(memberMission.getMission().getId())
                .status(memberMission.getStatus())
                .build();
    }
}
