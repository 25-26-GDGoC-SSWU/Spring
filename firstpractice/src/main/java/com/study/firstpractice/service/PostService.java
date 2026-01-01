package com.study.firstpractice.service;

import com.study.firstpractice.domain.Member;
import com.study.firstpractice.domain.Post;
import com.study.firstpractice.dto.PostRequestDto;
import com.study.firstpractice.dto.PostResponseDto;
import com.study.firstpractice.repository.MemberRepository;
import com.study.firstpractice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
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

    @Transactional(readOnly = true)
    public PostResponseDto getPost(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        return PostResponseDto.from(post);
    }
}
