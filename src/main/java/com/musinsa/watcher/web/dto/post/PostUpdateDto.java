package com.musinsa.watcher.web.dto.post;

import lombok.Getter;

@Getter
public class PostUpdateDto {

  private String title;
  private String content;

  public PostUpdateDto(String title, String content) {
    this.title = title;
    this.content = content;
  }
}
