package com.project.questApp.service;

import com.project.questApp.entity.Post;
import com.project.questApp.requests.PostCreateRequest;
import com.project.questApp.requests.PostUpdateRequest;
import com.project.questApp.responses.PostResponse;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<PostResponse> getAllPostsByUserId(Optional<Long> userId);
    Post getOnePostById(Long postId);
    Post createOnePost(PostCreateRequest newPostCreateRequest);
    Post updateOnePostById(Long postId, PostUpdateRequest newPostUpdateRequest);
    void deleteOnePostById(Long postId);
}
