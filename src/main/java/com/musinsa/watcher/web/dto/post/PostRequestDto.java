package com.musinsa.watcher.web.dto.post;

import com.musinsa.watcher.domain.post.Post;
import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostRequestDto implements Serializable {

  private String title;
  private String content;
  private String author;

  @Builder
  public PostRequestDto(String title, String content, String author) {
    this.title = title;
    this.content = content;
    this.author = author;
  }

  public Post toEntity() {
    return Post.builder().title(title).content(content).author(author).build();
  }

}
