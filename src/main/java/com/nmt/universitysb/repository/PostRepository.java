package com.nmt.universitysb.repository;
import com.nmt.universitysb.dto.PostDto;
import com.nmt.universitysb.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Optional<Post> findById(int id);

    @Query("select new com.nmt.universitysb.dto.PostDto(a.id, a.title, a.content, a.postTime, a.userId) " +
            "from Post a " +
            "where a.userId.id = :userId")
    List<PostDto> getPostByUserId(@Param("userId") int userId);
    Page<Post> findAllByTitleContaining(String keyword, Pageable pageable);
    Post save(Post f);
    void deleteById(int id);
}
