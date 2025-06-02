package umc.spring.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.validation.validator.StoreExistValidator;

import java.lang.annotation.*;

@Documented // 사용자 정의 어노테이션을 만들 때 붙임
@Constraint(validatedBy = StoreExistValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER }) // 어노테이션의 적용 범위를 지정
@Retention(RetentionPolicy.RUNTIME) // 어노테이션의 생명 주기를 지정 // RUNTIME: 실행 하는 동안에만 유효
public @interface ExistStore {
    String message() default "해당하는 가게가 존재하지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
