package hello.core.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter; // 1. Getter 추가 권장
import lombok.NoArgsConstructor; // 2. 기본 생성자 추가 권장
import java.time.LocalDateTime;

@Entity
@Getter // 데이터를 가져오기 위해 필요합니다.
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA 엔티티는 기본 생성자가 필수입니다.
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Builder
    public Member(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public Long getMemberId() {
        return 0L;
    }
}