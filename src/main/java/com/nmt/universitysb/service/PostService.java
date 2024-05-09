package com.nmt.universitysb.service;

import com.nmt.universitysb.dto.PostDto;
import com.nmt.universitysb.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> findAll();
    List<PostDto> findByUserId(int id);
    Page<Post> findAll(Pageable pageable);
    Optional<Post> findById(int id);
    Page<Post> findAllByTitleContaining(String keyword, Pageable pageable);
    Post save(Post f);
    void updatePostContent(int postId, String newContent);
    boolean deletePost(int id);
    Post addPost(Post post);
}
