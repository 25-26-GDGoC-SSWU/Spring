package hello.core.dto;

import hello.core.domain.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberDto {

    @Getter
    @NoArgsConstructor
    public static class SignupRequest {
        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        private String email;

        @NotBlank(message = "비밀번호는 필수입니다.")
        @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
        private String password;

        @NotBlank(message = "닉네임은 필수입니다.")
        @Size(min = 2, message = "닉네임은 최소 2자 이상이어야 합니다.")
        private String nickname;
    }

    @Getter
    @Builder
    public static class SignupResponse {
        private Long memberId;
        private String email;
        private String nickname;

        public static SignupResponse of(Member member) {
            return SignupResponse.builder()
                    .memberId(member.getId())
                    .email(member.getEmail())
                    .nickname(member.getNickname())
                    .build();
        }
    }
    @Getter
    @NoArgsConstructor
    public static class LoginRequest {
        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "이메일 형식이 아닙니다.")
        private String email;

        @NotBlank(message = "비밀번호는 필수입니다.")
        private String password;
    }

    @Getter
    @Builder
    public static class LoginResponse {
        private String message;
        private String email;

        public static LoginResponse success(String email) {
            return LoginResponse.builder()
                    .message("로그인에 성공하였습니다.")
                    .email(email)
                    .build();
        }
    }
}