package com.musinsa.watcher.web.dto.post;

import com.musinsa.watcher.domain.post.Post;
import java.io.Serializable;
import lombok.Getter;

@Getter
public class PostResponseDto implements Serializable {

  private String title;
  private String content;
  private String author;

  public PostResponseDto(Post entity) {
    this.title = entity.getTitle();
    this.content = entity.getContent();
    this.author = entity.getAuthor();
  }


}
