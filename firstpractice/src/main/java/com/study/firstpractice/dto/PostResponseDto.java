package com.study.firstpractice.dto;

import com.study.firstpractice.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostResponseDto {
    private Long postId;
    private String title;
    private String content;
    private Long memberId;
    private LocalDateTime createdAt;

    public static PostResponseDto from(Post post) {
        return new PostResponseDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getMember().getId(),
                post.getCreatedAt()
        );
    }
}
