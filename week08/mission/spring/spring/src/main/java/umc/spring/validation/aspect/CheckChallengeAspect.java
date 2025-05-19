package umc.spring.validation.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.expection.handler.MissionHandler;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.repository.MemberMissionRepository.MemberMissionRepository;

@Aspect
@Component
@RequiredArgsConstructor
public class CheckChallengeAspect {

    private final MemberMissionRepository memberMissionRepository;

    @Around("@annotation(umc.spring.validation.annotation.CheckChallenge)")
    public Object checkAlreadyChallenged(ProceedingJoinPoint joinPoint) throws Throwable {
        // 메서드 인자에서 memberId, missionId 찾아야 함 (Long 타입으로 가정)
        Object[] args = joinPoint.getArgs();
//        args[0] → memberId
//        args[1] → missionId
//        args[2] → request

        Long memberId = null;
        Long missionId = null;

        for (Object arg : args) {
            if (arg instanceof Long) {
                if (memberId == null) {
                    memberId = (Long) arg;
                } else {
                    missionId = (Long) arg;
                }
            }
        }

        if (memberId == null || missionId == null) {
            throw new MissionHandler(ErrorStatus._BAD_REQUEST); // 인자 문제
        }

        // 이미 도전 중인지 체크 (CHALLENGING 상태인 멤버미션이 있는지)
        boolean exists = memberMissionRepository.existsByMemberIdAndMissionIdAndStatus(memberId, missionId, MissionStatus.CHALLENGING);
        if (exists) {
            throw new MissionHandler(ErrorStatus.MISSION_ALREADY_CHALLENGED);
        }

        return joinPoint.proceed();
    }
}
