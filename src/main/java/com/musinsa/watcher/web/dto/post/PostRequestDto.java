package com.musinsa.watcher.web.dto.post;

import com.musinsa.watcher.domain.post.Post;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PostRequestDto {

  private String title;
  private String content;
  private String author;

  public Post toEntity() {
    return Post.builder().title(title).content(content).author(author).build();
  }

}
