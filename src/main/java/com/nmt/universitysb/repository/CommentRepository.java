package com.nmt.universitysb.repository;
import com.nmt.universitysb.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Optional<Comment> findById(int id);
    Comment save(Comment f);
    void deleteById(int id);
    @Query("select a from Comment a where a.postId.id = :postId")
    List<Comment> getCommentByPostId(@Param("postId") int postId);
//    Comment addComment(Comment comment);
}
