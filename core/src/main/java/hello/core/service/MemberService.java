package hello.core.service;

import hello.core.domain.Member;
import hello.core.dto.MemberDto;
import hello.core.exception.BusinessException;
import hello.core.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public MemberDto.SignupResponse signUp(MemberDto.SignupRequest request) {
        // 1. 이메일 중복 체크 -> BusinessException으로 고침
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("이미 존재하는 이메일입니다.", HttpStatus.BAD_REQUEST);
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        Member member = Member.builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .nickname(request.getNickname())
                .build();

        memberRepository.save(member);
        return MemberDto.SignupResponse.of(member);
    }

    public MemberDto.LoginResponse login(MemberDto.LoginRequest request) {
        // 2. 이메일 찾기 -> BusinessException(NOT_FOUND)으로 고침
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(()-> new BusinessException("가입되지 않은 이메일입니다.", HttpStatus.NOT_FOUND));

        // 3. 비밀번호 확인 -> BusinessException으로 고침
        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new BusinessException("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        return MemberDto.LoginResponse.success(member.getEmail());
    }
}