package hello.core.controller;

import hello.core.dto.MemberDto;
import hello.core.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<MemberDto.SignupResponse> signup(@Valid @RequestBody MemberDto.SignupRequest request) {
        MemberDto.SignupResponse response = memberService.signUp(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping("/login")
    public ResponseEntity<MemberDto.LoginResponse> login(@Valid @RequestBody MemberDto.LoginRequest request) {
        MemberDto.LoginResponse response = memberService.login(request);
        return ResponseEntity.ok(response);
    }
}