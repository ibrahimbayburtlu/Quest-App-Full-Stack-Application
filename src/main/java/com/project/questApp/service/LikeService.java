package com.project.questApp.service;

import com.project.questApp.entity.Like;
import com.project.questApp.requests.LikeCreateRequest;
import com.project.questApp.responses.LikeResponse;

import java.util.List;
import java.util.Optional;

public interface LikeService {
    List<LikeResponse> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId);

    Like createOneLike(LikeCreateRequest likeCreateRequest);

    Like getOneLikeById(Long likeId);

    void deleteOneLikeById(Long likeId);
}
