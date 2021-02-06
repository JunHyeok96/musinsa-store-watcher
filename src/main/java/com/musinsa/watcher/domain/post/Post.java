package com.musinsa.watcher.domain.post;

import com.musinsa.watcher.domain.BaseTimeEntity;
import com.musinsa.watcher.web.dto.post.PostUpdateDto;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Entity
public class Post extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  private String content;

  private String author;

  @Builder
  public Post(String title, String content, String author){
    this.title = title;
    this.content = content;
    this.author = author;
  }

  public void update(PostUpdateDto postUpdateDto){
    this.title = postUpdateDto.getTitle();
    this.content = postUpdateDto.getContent();
  }
}
