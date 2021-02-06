package com.musinsa.watcher.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.musinsa.watcher.domain.post.Post;
import com.musinsa.watcher.domain.post.master.PostRepository;
import com.musinsa.watcher.web.dto.post.PostRequestDto;
import com.musinsa.watcher.web.dto.post.PostUpdateDto;
import java.util.Optional;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class PostServiceTest {

  @Test
  @DisplayName("게시글을 저장 한다.")
  public void 게시글저장() {
    //given
    PostRepository mockPostRepository = mock(PostRepository.class);
    Post mockPost = mock(Post.class);
    PostRequestDto mockRequestDto = mock(PostRequestDto.class);
    PostService postService = new PostService(mockPostRepository);
    when(mockRequestDto.toEntity()).thenReturn(mockPost);
    //when
    postService.savePost(mockRequestDto);
    //then
    verify(mockPostRepository, times(1)).save(eq(mockPost));
  }

  @Test
  @DisplayName("게시글을 조회 한다.")
  public void 게시글조회() {
    //given
    long postId = 1L;
    PostRepository mockPostRepository = mock(PostRepository.class);
    Post mockPost = mock(Post.class);
    Optional<Post> mockOptional = mock(Optional.class);
    PostService postService = new PostService(mockPostRepository);
    when(mockPostRepository.findById(eq(postId))).thenReturn(mockOptional);
    when(mockOptional.orElseThrow(any())).thenReturn(mockPost);
    //when
    postService.findPost(postId);
    //then
    verify(mockPostRepository, times(1)).findById(eq(postId));
  }

  @Test(expected = IllegalArgumentException.class)
  @DisplayName("존재하지 않는 게시글을 조회 한다.")
  public void 존재하지않는게시글조회() {
    //given
    long postId = 1L;
    PostRepository mockPostRepository = mock(PostRepository.class);
    Post mockPost = mock(Post.class);
    Optional<Post> mockOptional = mock(Optional.class);
    PostService postService = new PostService(mockPostRepository);
    when(mockPostRepository.findById(eq(postId))).thenReturn(Optional.empty());
    //when
    postService.findPost(postId);
    //then
    verify(mockPostRepository, times(1)).findById(eq(postId));
  }

  @Test
  @DisplayName("게시글을 삭제 한다.")
  public void 게시글삭제() {
    //given
    long postId = 1L;
    PostRepository mockPostRepository = mock(PostRepository.class);
    Post mockPost = mock(Post.class);
    Optional<Post> mockOptional = mock(Optional.class);
    PostService postService = new PostService(mockPostRepository);
    when(mockPostRepository.findById(eq(postId))).thenReturn(mockOptional);
    when(mockOptional.orElseThrow(any())).thenReturn(mockPost);
    doNothing().when(mockPostRepository).delete(eq(mockPost));
    //when
    postService.deletePost(postId);
    //then
    verify(mockPostRepository, times(1)).delete(eq(mockPost));
    verify(mockPostRepository, times(1)).findById(eq(postId));
  }

  @Test(expected = IllegalArgumentException.class)
  @DisplayName("존재하지 않는 게시글을 삭제 한다.")
  public void 존재하지않는게시글삭제() {
    //given
    long postId = 1L;
    PostRepository mockPostRepository = mock(PostRepository.class);
    Post mockPost = mock(Post.class);
    Optional<Post> mockOptional = mock(Optional.class);
    PostService postService = new PostService(mockPostRepository);
    when(mockPostRepository.findById(eq(postId))).thenReturn(Optional.empty());
    when(mockOptional.orElseThrow(any())).thenReturn(mockPost);
    doNothing().when(mockPostRepository).delete(eq(mockPost));
    //when
    postService.deletePost(postId);
    //then
    verify(mockPostRepository, times(1)).findById(eq(postId));
    verify(mockPostRepository, never()).delete(eq(mockPost));
  }

  @Test
  @DisplayName("게시글을 수정 한다.")
  public void 게시글수정() {
    //given
    long postId = 1L;
    PostRepository mockPostRepository = mock(PostRepository.class);
    Post mockPost = mock(Post.class);
    PostUpdateDto postUpdateDto = mock(PostUpdateDto.class);
    Optional<Post> mockOptional = mock(Optional.class);
    PostService postService = new PostService(mockPostRepository);
    when(mockPostRepository.findById(eq(postId))).thenReturn(mockOptional);
    when(mockOptional.orElseThrow(any())).thenReturn(mockPost);
    when(mockPost.update(eq(postUpdateDto))).thenReturn(mockPost);
    //when
    postService.updatePost(postId, postUpdateDto);
    //then
    verify(mockPostRepository, times(1)).findById(eq(postId));
    verify(mockPost, times(1)).update(postUpdateDto);
  }

  @Test(expected = IllegalArgumentException.class)
  @DisplayName("존재하지 않는 게시글을 수정 한다.")
  public void 존재하지않는게시글수정() {
    //given
    long postId = 1L;
    PostRepository mockPostRepository = mock(PostRepository.class);
    Post mockPost = mock(Post.class);
    PostUpdateDto postUpdateDto = mock(PostUpdateDto.class);
    PostService postService = new PostService(mockPostRepository);
    when(mockPostRepository.findById(eq(postId))).thenReturn(Optional.empty());
    when(mockPost.update(eq(postUpdateDto))).thenReturn(mockPost);
    //when
    postService.updatePost(postId, postUpdateDto);
    //then
    verify(mockPostRepository, times(1)).findById(eq(postId));
    verify(mockPost, never()).update(postUpdateDto);
  }
}