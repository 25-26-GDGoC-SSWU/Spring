package hello.core.domain;

import jakarta.persistence.*;
import lombok.Getter; // 1. Getter 추가 권장
import lombok.NoArgsConstructor; // 2. 기본 생성자 추가 권장
import java.time.LocalDateTime;

@Entity
@Getter // 데이터를 가져오기 위해 필요합니다.
@NoArgsConstructor // JPA 엔티티는 기본 생성자가 필수입니다.
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberid")
    private Long memberId;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // 생성일자 자동화를 위해 잘 작성하셨습니다!
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    // 테스트용 데이터를 넣을 때 편리하도록 생성자를 추가하면 좋습니다.
    public Member(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }
}