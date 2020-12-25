package com.musinsa.watcher.domain.log;

import com.musinsa.watcher.domain.BaseTimeEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class AccessLog extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String ip;

  @Column(nullable = false)
  private String agent;

  @Column(nullable = false)
  private String url;

  private String parameter;

  @Builder
  public AccessLog(String ip, String agent, String url, String parameter) {
    this.ip=ip;
    this.agent=agent;
    this.url=url;
    this.parameter=parameter;
  }

}