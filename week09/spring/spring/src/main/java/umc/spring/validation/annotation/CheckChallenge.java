package umc.spring.validation.annotation;

import java.lang.annotation.*;

@Documented // 사용자 정의 어노테이션을 만들 때 붙임
@Target( { ElementType.METHOD}) // 어노테이션의 적용 범위를 지정
@Retention(RetentionPolicy.RUNTIME) // 어노테이션의 생명 주기를 지정 // RUNTIME: 실행 하는 동안에만 유효
public @interface CheckChallenge {
}
