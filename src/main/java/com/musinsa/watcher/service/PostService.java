package com.musinsa.watcher.service;

import com.musinsa.watcher.domain.post.Post;
import com.musinsa.watcher.domain.post.master.PostRepository;
import com.musinsa.watcher.web.dto.post.PostRequestDto;
import com.musinsa.watcher.web.dto.post.PostResponseDto;
import com.musinsa.watcher.web.dto.post.PostUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

  private final PostRepository postRepository;

  @Transactional(readOnly = true)
  public PostResponseDto findPost(Long id) {
    Post post = postRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
    return new PostResponseDto(post);
  }

  @Transactional
  public void savePost(PostRequestDto postRequestDto) {
    postRepository.save(postRequestDto.toEntity());
  }

  @Transactional
  public void updatePost(Long id, PostUpdateDto postUpdateDto){
    //TODO 권한설정
    Post post = postRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
    post.update(postUpdateDto);
  }

  @Transactional
  public void deletePost(Long id){
    //TODO 권한설정
    Post post = postRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
    postRepository.delete(post);
  }

}
