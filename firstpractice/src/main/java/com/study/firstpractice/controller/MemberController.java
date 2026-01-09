package com.study.firstpractice.controller;

import com.study.firstpractice.dto.LoginRequestDto;
import com.study.firstpractice.dto.LoginResponseDto;
import com.study.firstpractice.dto.SignupRequestDto;
import com.study.firstpractice.dto.SignupResponseDto;
import com.study.firstpractice.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public SignupResponseDto signup(@Valid @RequestBody SignupRequestDto request){
        return memberService.signup(request);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto request){
        memberService.login(request);
        return ResponseEntity.ok(new LoginResponseDto("로그인 성공"));
    }
}
