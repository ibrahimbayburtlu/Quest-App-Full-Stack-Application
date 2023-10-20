package com.project.questApp.service;

import com.project.questApp.entity.Like;
import com.project.questApp.entity.Post;
import com.project.questApp.entity.User;
import com.project.questApp.repository.PostRepository;
import com.project.questApp.requests.PostCreateRequest;
import com.project.questApp.requests.PostUpdateRequest;
import com.project.questApp.responses.LikeResponse;
import com.project.questApp.responses.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{

    private PostRepository postRepository;
    private UserService userService;
    private LikeService likeService;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public void setLikeService(LikeService likeService){
        this.likeService = likeService;
    }

    @Override
    public List<PostResponse> getAllPostsByUserId(Optional<Long> userId) {
        List<Post> list;
        if (userId.isPresent()){
            list = postRepository.findByUserId(userId);
        }else {
            list = postRepository.findAll();
        }
        return list.stream().map(p ->{
            List<LikeResponse> likes = likeService.getAllLikesWithParam(Optional.empty(),Optional.of(p.getId()));
            return new PostResponse(p,likes);}).collect(Collectors.toList());
    }

    @Override
    public Post getOnePostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    @Override
    public Post createOnePost(PostCreateRequest newPostCreateRequest) {
        User user = userService.getOneUserById(newPostCreateRequest.getUserId());
        if (user == null)
            return null;

        Post toSave = new Post();
        toSave.setId(newPostCreateRequest.getId());
        toSave.setText(newPostCreateRequest.getText());
        toSave.setTitle(newPostCreateRequest.getTitle());
        toSave.setUser(user);
        return postRepository.save(toSave);
    }

    @Override
    public Post updateOnePostById(Long postId, PostUpdateRequest newPostUpdateRequest) {
        Optional <Post> post = postRepository.findById(postId);
        if (post.isEmpty()){
            return null;
        }
        Post updatedPost = post.get();
        updatedPost.setText(newPostUpdateRequest.getText());
        updatedPost.setTitle(newPostUpdateRequest.getTitle());
        postRepository.save(updatedPost);
        return updatedPost;
    }

    @Override
    public void deleteOnePostById(Long postId) {
        postRepository.deleteById(postId);
    }
}
