package com.project.questApp.service;

import com.project.questApp.entity.Like;
import com.project.questApp.entity.Post;
import com.project.questApp.entity.User;
import com.project.questApp.repository.LikeRepository;
import com.project.questApp.requests.LikeCreateRequest;
import com.project.questApp.responses.LikeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeServiceImpl implements LikeService{

    private LikeRepository likeRepository;
    private UserService userService;
    private PostService postService;

    @Autowired
    public LikeServiceImpl(LikeRepository likeRepository, UserService userService, PostService postService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }


    @Override
    public List<LikeResponse> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId) {
        List<Like> list;
        if (userId.isPresent() && postId.isPresent()){
            list = likeRepository.findByUserIdAndPostId(userId.get(),postId.get());
        }else if (userId.isPresent()){
            list = likeRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            list = likeRepository.findByPostId(postId.get());
        }else {
            list = likeRepository.findAll();
        }
        return list.stream().map(LikeResponse::new).collect(Collectors.toList());
    }


    @Override
    public Like getOneLikeById(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

    @Override
    public Like createOneLike(LikeCreateRequest likeCreateRequest) {
        User user = userService.getOneUserById(likeCreateRequest.getUserId());
        Post post = postService.getOnePostById(likeCreateRequest.getPostId());
        if (user != null && post != null){
            Like likeToSave = new Like();
            likeToSave.setId(likeCreateRequest.getId());
            likeToSave.setPost(post);
            likeToSave.setUser(user);
            return likeRepository.save(likeToSave);
        }
        return null;
    }

    @Override
    public void deleteOneLikeById(Long likeId) {
        likeRepository.deleteById(likeId);
    }
}
