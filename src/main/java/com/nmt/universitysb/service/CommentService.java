package com.nmt.universitysb.service;

import com.nmt.universitysb.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> findAll();
    Optional<Comment> findById(int id);
    Comment save(Comment f);
    void updateCommentContent(int commentId, String newContent);
    boolean deleteComment(int id);
    List<Comment> getCommentByPostId(int postId);
    Comment addComment(Comment comment);
}
