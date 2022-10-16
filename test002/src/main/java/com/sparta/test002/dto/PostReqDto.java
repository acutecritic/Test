package com.sparta.test002.dto;


import com.sparta.test002.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor //여기에 필드에 쓴 모든생성자만 만들어줌
public class PostReqDto {
    private String title;
    private String author;
    private String content;
    private String password;

    public Post toEntity(){
        return new Post(title,author,content,password);
    }

}
