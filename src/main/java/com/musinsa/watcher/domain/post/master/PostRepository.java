package com.musinsa.watcher.domain.post.master;

import com.musinsa.watcher.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
