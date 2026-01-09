package com.study.firstpractice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignupResponseDto {
    private Long memberId;
    private String email;
    private String nickname;
}
