package com.sparta.test002.dto;

import com.sparta.test002.entity.Post;

import java.time.LocalDateTime;

public class PostDetailResDto {
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;
    private Long id;
    private String title;
    private String author;
    private String content;

    public PostDetailResDto(Post post) {
        createAt = post.getCreatedAt();

        updatedAt = post.getUpdatedAt();

        id = post.getId();

        title = post.getTitle();

        author = post.getAuthor();

        content = post.getContent();
    }
}
