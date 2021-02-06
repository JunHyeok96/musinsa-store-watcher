package com.musinsa.watcher.domain.post;

import static org.junit.Assert.*;

import com.musinsa.watcher.domain.post.master.PostRepository;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@RequiredArgsConstructor
@DataJpaTest
public class PostTest {

  private final PostRepository postRepository;

  @Test
  @DisplayName("게시글을 등록 한다.")
  public void 게시글등록(){
    String title = "문의 사항입니다.";
    String content = "본문 내용입니다.";
    String author = "작성자입니다";
    Post post = Post.builder()
        .title(title)
        .content(content)
        .author(author)
        .build();
    postRepository.save(post);

    Post result = postRepository.findAll().get(0);

    assertEquals(result.getAuthor(), post.getAuthor());
    assertEquals(result.getContent(), post.getContent());
    assertEquals(result.getTitle(), post.getTitle());
  }

}