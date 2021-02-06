package com.musinsa.watcher.web.dto.post;

import com.musinsa.watcher.domain.post.Post;
import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostResponseDto implements Serializable {

  private String title;
  private String content;
  private String author;

  @Builder
  public PostResponseDto(Post entity) {
    this.title = entity.getTitle();
    this.content = entity.getContent();
    this.author = entity.getAuthor();
  }


}
