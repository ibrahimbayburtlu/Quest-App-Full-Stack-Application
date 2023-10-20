package com.project.questApp.service;

import com.project.questApp.entity.Comment;
import com.project.questApp.requests.CommentCreateRequest;
import com.project.questApp.requests.CommentUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId);

    Comment getOneCommentById(Long commentId);

    Comment createOneComment(CommentCreateRequest commentCreateRequest);

    Comment updateOneComment(Long commentId, CommentUpdateRequest commentUpdateRequest);

    void deleteOneCommentById(Long commentId);
}
