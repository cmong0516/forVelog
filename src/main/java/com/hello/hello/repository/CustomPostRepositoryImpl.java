package com.hello.hello.repository;


import static com.hello.hello.domain.entity.QPost.post;

import com.hello.hello.domain.entity.Post;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomPostRepositoryImpl implements CustomPostRepository{

    private final JPAQueryFactory jpaQueryFactory;




    public List<Post> findPostsByTitleAndContent(String title, String content) {
        return jpaQueryFactory.selectFrom(post)
                .where(
                        containsTitle(title),
                        containsContent(content)
                )
                .fetch();
    }

    private BooleanExpression containsTitle(String title) {
        if (title != null && !title.isEmpty()) {
            return post.title.contains(title);
        }

        return null;
    }

    private BooleanExpression containsContent(String content) {
        if (content != null && !content.isEmpty()) {
            return post.content.contains(content);
        }
        return null;
    }
}
