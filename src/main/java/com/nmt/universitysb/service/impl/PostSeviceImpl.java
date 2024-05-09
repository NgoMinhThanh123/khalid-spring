package com.nmt.universitysb.service.impl;
import com.nmt.universitysb.dto.PostDto;
import com.nmt.universitysb.model.Post;
import com.nmt.universitysb.model.User;
import com.nmt.universitysb.repository.PostRepository;
import com.nmt.universitysb.repository.UserRepository;
import com.nmt.universitysb.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostSeviceImpl implements PostService {
    @Autowired
    private PostRepository postRepo;
    @Autowired
    private UserRepository userRepo;

    @Override
    public List<Post> findAll() {
        return this.postRepo.findAll();
    }

    @Override
    public List<PostDto> findByUserId(int id) {
        return this.postRepo.getPostByUserId(id);
    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return this.postRepo.findAll(pageable);
    }

    @Override
    public Optional<Post> findById(int id) {
        return this.postRepo.findById(id);
    }

    @Override
    public Page<Post> findAllByTitleContaining(String keyword, Pageable pageable) {
        return this.postRepo.findAllByTitleContaining(keyword, pageable);
    }

    @Override
    public Post save(Post f) {
        return this.postRepo.save(f);
    }

    @Override
    public void updatePostContent(int postId, String newContent) {
        Optional<Post> optionalPost = this.postRepo.findById(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setContent(newContent);
            this.postRepo.save(post);
        }
    }

    @Override
    public boolean deletePost(int id) {
        this.postRepo.deleteById(id);
        return true;
    }

    @Override
    public Post addPost(Post post) {
        post.setPostTime(new Date());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User u = this.userRepo.getUserByUsername(authentication.getName());
        post.setUserId(u);
        return this.postRepo.save(post);
    }

}
