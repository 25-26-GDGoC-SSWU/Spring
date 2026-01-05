package hello.core.dto;

import hello.core.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

// 응답 DTO
@Getter
@AllArgsConstructor
public class PostResponse {
    private Long postId;
    private String title;
    private String content;
    private Long memberId;
    private LocalDateTime createdAt;

    public static PostResponse from(Post post) {
        return new PostResponse(
                post.getPostId(),
                post.getTitle(),
                post.getContent(),
                post.getMember().getMemberId(),
                post.getCreatedAt()
        );
    }
}