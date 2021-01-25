package com.board.foo.dto;

import com.board.foo.exception.UniqueEmailValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = UniqueEmailValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {

    String message() default "이미 존재하는 이메일입니다";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}