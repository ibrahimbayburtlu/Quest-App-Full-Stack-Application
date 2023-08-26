package com.project.questApp.service;

import com.project.questApp.entity.Post;
import com.project.questApp.entity.User;
import com.project.questApp.repository.PostRepository;
import com.project.questApp.requests.PostCreateRequest;
import com.project.questApp.requests.PostUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{

    private PostRepository postRepository;
    private UserService userService;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @Override
    public List<Post> getAllPostsByUserId(Optional<Long> userId) {
        if (userId.isPresent()){
            return postRepository.findByUserId(userId);
        }
        return postRepository.findAll();
    }

    @Override
    public Post getOnePostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    @Override
    public Post createOnePost(PostCreateRequest newPostCreateRequest) {
        User user = userService.getOneUser(newPostCreateRequest.getUserId());
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
