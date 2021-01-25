package com.board.foo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import com.board.foo.dto.MemberForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.hamcrest.CoreMatchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Validator validator;

    @Test
    void 회원가입폼() throws Exception{
        this.mockMvc.perform(get("/members/signup"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("memberForm"));
    }

    //Valid 유효성 검사 포함
    @Test
    void 회원가입() throws Exception{

        String content = objectMapper.writeValueAsString(new MemberForm("asdm", "dale", "s"));

        MemberForm registerRequestDto = new MemberForm("admin", "dale", "s");
        Set<ConstraintViolation<MemberForm>> constraintViolations = validator.validate(registerRequestDto);

        assertThat(constraintViolations)
            .extracting(ConstraintViolation::getMessage)
            .containsOnly( //DTO에 위반되었을 때 표시할 message
                "이메일 형식으로 적어라!!"
            );

        this.mockMvc.perform(post("/members/signup")
            .content(content)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    //이메일 중복 테스트
    @Test
    void test_must_succeed() {
        // Given
        MemberForm registerRequestDto = new MemberForm("admin@admin.com", "dale", "s");

        // When
        Set<ConstraintViolation<MemberForm>> violations = validator.validate(registerRequestDto);

        // Then
        assertThat(violations).isNotEmpty();
    }

}