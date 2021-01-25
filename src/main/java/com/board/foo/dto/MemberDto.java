package com.board.foo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class SignInReq{

        @NotEmpty(message = "아이디는 필수 입력 값입니다.")
        @Email(message = "아이디 형식에 맞지 않습니다.")
        @Size(max = 30, message = "아이디는 30자 이하로 입력해주세요.")
        private String userId;

        @NotBlank(message = "패스워드는 필수 입력 값입니다.")
        @Size(max = 16, message = "비밀번호는 15자 이하로 입력해주세요.")
        private String password;

        @NotBlank(message = "이름은 필수 입력 값입니다.")
        @Size(max = 20, message = "이름은 20자 이하로 입력해주세요.")
        private String name;

        @Builder
        public SignInReq(String userId, String password, String name){
            this.userId = userId;
            this.password = password;
            this.name = name;
        }
    }

}
