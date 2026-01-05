package hello.core.controller;

import hello.core.dto.PostResponse;
import hello.core.service.Postservice;
import hello.core.dto.PostCreateRequest;
import hello.core.dto.PostResponse;
import hello.core.service.Postservice;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final Postservice postService;

    @PostMapping
    public PostResponse createPost(@RequestBody PostCreateRequest requestDto){
        return postService.createPost(requestDto);
    }

    @GetMapping("/{postId}")
    public PostResponse getPost(@PathVariable Long postId){
        return postService.getPost(postId);
    }
}