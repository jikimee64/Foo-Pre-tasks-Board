package com.board.foo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class MemberForm {

    @NotEmpty(message = "아이디는 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 적어라!!")
    @UniqueEmail(message = "이메일 중복!!")
    @Size(max = 30, message = "아이디는 30자 이하로 입력해주세요.")
    private String email;

    @NotEmpty(message = "패스워드는 필수 입력 값입니다.")
    @Size(max = 16, message = "비밀번호는 15자 이하로 입력해주세요.")
    private String password;

    @NotEmpty(message = "이름은 필수 입력 값입니다.")
    @Size(max = 20, message = "이름은 20자 이하로 입력해주세요.")
    private String name;

}
