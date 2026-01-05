package hello.core.service;

import hello.core.dto.PostCreateRequest;
import hello.core.dto.PostResponse;
import hello.core.repository.MemberRepository;
import hello.core.repository.PostRepository;
import hello.core.domain.Member;
import hello.core.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class Postservice {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public PostResponse createPost(PostCreateRequest request) {

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .member(member)
                .build();


        Post savedPost = postRepository.save(post);
        return PostResponse.from(savedPost);
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        return PostResponse.from(post);
    }
}