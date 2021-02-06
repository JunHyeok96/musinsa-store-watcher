package com.musinsa.watcher.web;

import com.musinsa.watcher.service.PostService;
import com.musinsa.watcher.web.dto.post.PostRequestDto;
import com.musinsa.watcher.web.dto.post.PostResponseDto;
import com.musinsa.watcher.web.dto.post.PostUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PostController {

  private final PostService postService;

  @GetMapping("/api/v1/post")
  public PostResponseDto findPost(@RequestParam long id) {
    return postService.findPost(id);
  }

  @PostMapping("/api/v1/post")
  public void savePost(@RequestBody PostRequestDto requestDto) {
    postService.savePost(requestDto);
  }

  @PutMapping("/api/v1/post")
  public void updatePost(long id, @RequestBody PostUpdateDto postUpdateDto) {
    postService.updatePost(id, postUpdateDto);
  }

  @DeleteMapping("/api/v1/post")
  public void deletePost(long id) {
    postService.deletePost(id);
  }
}
