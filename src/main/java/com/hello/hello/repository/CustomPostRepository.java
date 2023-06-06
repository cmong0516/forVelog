package com.hello.hello.repository;

import com.hello.hello.domain.entity.Post;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomPostRepository {
    List<Post> findPostsByTitleAndContent(String title, String content);
}
