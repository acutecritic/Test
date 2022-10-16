package com.sparta.test002.service;

import com.sparta.test002.dto.*;
import com.sparta.test002.entity.Post;
import com.sparta.test002.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;

    // 게시글 전체 조회- 전체 조회
    //1. DB에서 가져오기
    //2. DTO로 변환
    //3. 리턴
    public List<PostResDto> getAll(){

        //[1, 2, 3, 4, 5]
        //["1", "2", "3", "4", "5"]
        List<PostResDto> result = new ArrayList<>(); // 1.DB에서 가져오기

        List<Post> postList = postRepository.findAll(); // 2. DTO로 변환

        for(Post post : postList){//2-1하나씩 다꺼내서 DTO로 변환 시킨다음 원래 선언할 DTO에게 변환을 해준다
            PostResDto postResDto = new PostResDto(post);
            result.add(postResDto); //생성했던 List에서 반환을 함
        }

        return result;
    }
//    List<PostResDto>list = postRepository.findAll().stream().map(
////    post -> new PostReDto(Post) //post가 들어있었는데 PostResDto로 바꾸겠다.
////        ).collect(Collectors.tList())


//    return postRepository.findAll().stream().map(
//            PostResDto::new
//    ).collect(Collectors.toList());
//    )

    // 게시글 단건 조회 - id가 필요!!!!!
    //1. DB에서 가져온다.(레퍼지토리에 요청한다)
    //2. DTO로 변환한다.
    //3. 리턴한다.
    public PostResDto getOne(Long id){ //Optional 박스로 씌운거다

        Optional<Post> optionalPost = postRepository.findById(id);

        if(optionalPost.isEmpty()){ //Optional 박스가 비워져있니?
            throw new IllegalArgumentException();
        }else{//Optional 박스가 차있으면 가져와라
            return new PostResDto(optionalPost.get());
        }
//            //Post post = postRepository.findById(id).orElseThrow(
//                ()-> new IllegalArgumentException()
//              );

//              return new PostResDto(post);
    }

    // 게시글 저장 - 게시글 데이터가 필요!!!!!
    @Transactional
    public PostResDto create(PostReqDto postReqDto){
        //저장을 시킬때 Entity로 바꿔서 저장을 시켜주는 거임ㅇㅇ
        Post post = postReqDto.toEntity();

        postRepository.save(post);

        return new PostResDto(post);

    }

    // 비밀번호 확인 - 확인할 비밀번호, 게시글 id, 원래 비밀번호(db에서 비미ㄹ번호를 가져와야한다)
    // 1. 확인할 게시물을 가져온다.
    // 2. 비교한다.
    public PasswordCheckResDto passwordCheck(Long id, PasswordCheckReqDto passwordCheckReqDto){

        Optional<Post> optionalPost = postRepository.findById(id);

        //postRepository.findById(id0.orElseThrow(
//        ()-> new IllegalArgumentException()
//                );

        //값이 없다면
        if(optionalPost.isEmpty()){//값이 없으면
            throw new IllegalArgumentException();
        }else{
            // 내부에 값이 존재하므로 값을 꺼낸다.
            Post post = optionalPost.get();

            // 비밀번호 값이 일치하는지 확인한다.
            boolean match = post.passwordMatch(passwordCheckReqDto.getPassword()); //확인하는 비밀번호가 들어가있습니다.

            // 원하는 리턴타입을 만든 후 리턴한다.
            return new PasswordCheckResDto(match);
        }

    }

    // 게시글 수정
    // 1. db에 저장된 수정할 게시글을 가져온다.
    // 2. 게시글을 수정한다.
    // 3. db에 수정된 게시글을 저장한다.
    @Transactional//설정이 우선시되게 된다(안에서 DB입장에서 데이터가 변하면 @Transactional)
    public PostResDto update(Long id, PostReqDto postReqDto){

        Optional<Post> optionalPost = postRepository.findById(id);

        //게시글이 없다면
        if(optionalPost.isEmpty()){
            throw new IllegalArgumentException();
        }else{
            Post post = optionalPost.get();

            post.update(
                    postReqDto.getTitle(),
                    postReqDto.getAuthor(),
                    postReqDto.getContent(),
                    postReqDto.getPassword());

            postRepository.save(post);

            return new PostResDto(post);
        }

    }
    // 게시글 삭제
    @Transactional
    public PostDeleteResDto delete(Long id){

        postRepository.deleteById(id);

        return new PostDeleteResDto(true);

    }

}