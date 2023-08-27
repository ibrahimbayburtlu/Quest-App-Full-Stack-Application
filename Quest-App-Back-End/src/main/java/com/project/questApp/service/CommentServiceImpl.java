package com.project.questApp.service;

import com.project.questApp.entity.Comment;
import com.project.questApp.entity.Post;
import com.project.questApp.entity.User;
import com.project.questApp.repository.CommentRepository;
import com.project.questApp.requests.CommentCreateRequest;
import com.project.questApp.requests.CommentUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    private CommentRepository commentRepository;
    private UserService userService;
    private PostService postService;

    public CommentServiceImpl(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    @Override
    public List<Comment> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {
        if (userId.isPresent() && postId.isPresent()){
            return commentRepository.findByUserIdAndPostId(userId.get(),postId.get());
        }else if (userId.isPresent()){
            return commentRepository.findByUserId(userId.get());
        }else if (postId.isPresent()){
            return commentRepository.findByPostId(postId.get());
        }else {
            return commentRepository.findAll();
        }
    }

    @Override
    public Comment getOneCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    @Override
    public Comment createOneComment(CommentCreateRequest commentCreateRequest) {
        User user = userService.getOneUserById(commentCreateRequest.getUserId());
        Post post = postService.getOnePostById(commentCreateRequest.getPostId());
        if (user != null && post != null){
            Comment comment = new Comment();
            comment.setId(commentCreateRequest.getId());
            comment.setPost(post);
            comment.setUser(user);
            comment.setText(commentCreateRequest.getText());
            return commentRepository.save(comment);
        }
        return null;
    }

    @Override
    public Comment updateOneComment(Long commentId, CommentUpdateRequest commentUpdateRequest) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()){
            Comment updatedComment = comment.get();
            updatedComment.setText(commentUpdateRequest.getText());
            commentRepository.save(updatedComment);
        }
        return null;
    }

    @Override
    public void deleteOneCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
    }

}
