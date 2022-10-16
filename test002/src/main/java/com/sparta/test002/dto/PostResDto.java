package com.sparta.test002.dto;

import com.sparta.test002.entity.Post;

import java.time.LocalDateTime;

public class PostResDto {
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private Long id;
    private String title;
    private String author;


    public PostResDto(Post post){

        createdAt = post.getCreatedAt();
        updateAt = post.getUpdatedAt();
        id = post.getId();
        title = post.getTitle();
        author = post.getAuthor();

    }

}
