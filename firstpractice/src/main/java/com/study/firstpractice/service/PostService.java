package com.study.firstpractice.service;

import com.study.firstpractice.domain.Member;
import com.study.firstpractice.domain.Post;
import com.study.firstpractice.dto.PostRequestDto;
import com.study.firstpractice.dto.PostResponseDto;
import com.study.firstpractice.exception.ErrorCode;
import com.study.firstpractice.exception.GeneralException;
import com.study.firstpractice.repository.MemberRepository;
import com.study.firstpractice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    @Transactional
    public PostResponseDto createPost(PostRequestDto requestDto){
        Member member = memberRepository.findById(requestDto.getMemberId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        Post post = new Post(
                requestDto.getTitle(),
                requestDto.getContent(),
                member
        );

        Post savedPost = postRepository.save(post);

        return PostResponseDto.from(savedPost);
    }

    //[2주차 추가] 공통 예외 처리 사용
    public PostResponseDto getPost(Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new GeneralException(ErrorCode.RESOURCE_NOT_FOUND));

        return PostResponseDto.from(post);
    }
}
