package com.project.questApp.service;

import com.project.questApp.entity.Like;
import com.project.questApp.requests.LikeCreateRequest;

import java.util.List;
import java.util.Optional;

public interface LikeService {
    List<Like> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId,Optional<Long> likeId);

    Like createOneLike(LikeCreateRequest likeCreateRequest);

    Like getOneLikeById(Long likeId);

    void deleteOneLikeById(Long likeId);
}
