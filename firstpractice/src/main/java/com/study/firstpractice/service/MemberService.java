package com.study.firstpractice.service;

import com.study.firstpractice.domain.Member;
import com.study.firstpractice.dto.LoginRequestDto;
import com.study.firstpractice.dto.SignupRequestDto;
import com.study.firstpractice.dto.SignupResponseDto;
import com.study.firstpractice.exception.ErrorCode;
import com.study.firstpractice.exception.GeneralException;
import com.study.firstpractice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public SignupResponseDto signup(SignupRequestDto request){

        if(memberRepository.existsByEmail(request.getEmail())){
            throw new GeneralException(ErrorCode.EMAIL_DUPLICATED);
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        Member member = new Member(
                request.getEmail(),
                encodedPassword,
                request.getNickname()
        );

        Member saved = memberRepository.save(member);

        return new SignupResponseDto(
                saved.getId(),
                saved.getEmail(),
                saved.getNickname()
        );
    }

    public void login(LoginRequestDto request){
        //이메일 일치 여부 검사
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new GeneralException(ErrorCode.LOGIN_FAILED));

        //비밀번호 일치 여부 검사
        if(!passwordEncoder.matches(request.getPassword(), member.getPassword())){
            throw new GeneralException(ErrorCode.LOGIN_FAILED);
        }
    }
}
