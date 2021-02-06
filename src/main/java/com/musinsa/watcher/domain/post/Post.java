package com.musinsa.watcher.domain.post;

import com.musinsa.watcher.domain.BaseTimeEntity;
import com.musinsa.watcher.web.dto.post.PostUpdateDto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class Post extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  @Lob
  @Column(nullable = false)
  private String content;

  @Column(nullable = false)
  private String author;

  @Builder
  public Post(String title, String content, String author){
    this.title = title;
    this.content = content;
    this.author = author;
  }

  public Post update(PostUpdateDto postUpdateDto){
    this.title = postUpdateDto.getTitle();
    this.content = postUpdateDto.getContent();
    return this;
  }
}
