package com.sparta.test002.controller;

import com.sparta.test002.dto.*;
import com.sparta.test002.service.PostService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // json형식으로 (데이터로)
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    //게시글 전체 조회
    @GetMapping("/posts")
    public List<PostResDto> getAll() {

        return postService.getAll();
    }

    //게시글 단건 조회
    @GetMapping("/posts/{id}")
    public PostResDto getOneById(@PathVariable("id") Long id) {

        return postService.getOne(id);
    }

    // 게시글 생성
    // Http body에 데이터가 담겨있다.
    @PostMapping("/posts")
    public PostResDto createPost(@RequestBody PostReqDto postReqDto) {

        return postService.create(postReqDto);

    }

    // 비밀번호 확인
    // Http body에 데이터가 담겨있다.
    @PostMapping("/posts/{id}")
    public PasswordCheckResDto passwordCheck(@PathVariable("id") Long id, @RequestBody PasswordCheckReqDto passwordCheckReqDto) {

        return postService.passwordCheck(id, passwordCheckReqDto);

    }

    // 게시글 수정
    // 게시글 id, 바꿀 데이터가 필요
    @PutMapping("/posts/{id}")
    public PostResDto updatePost(@PathVariable("id") Long id, @RequestBody PostReqDto postReqDto) {

        return postService.update(id, postReqDto);

    }

    // 게시글 삭제
    @DeleteMapping("/posts/{id}")
    public PostDeleteResDto deletePost(@PathVariable("id") Long id) {

        return postService.delete(id);

    }


}
