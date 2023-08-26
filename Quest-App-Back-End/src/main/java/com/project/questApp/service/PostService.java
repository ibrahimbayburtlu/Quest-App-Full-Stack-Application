package com.project.questApp.service;

import com.project.questApp.entity.Post;
import com.project.questApp.requests.PostCreateRequest;
import com.project.questApp.requests.PostUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> getAllPostsByUserId(Optional<Long> userId);
    Post getOnePostById(Long postId);
    Post createOnePost(PostCreateRequest newPostCreateRequest);
    Post updateOnePostById(Long postId, PostUpdateRequest newPostUpdateRequest);
    void deleteOnePostById(Long postId);
}
