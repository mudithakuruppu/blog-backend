package edu.wixze.com.service;

import edu.wixze.com.dto.Post;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {
    Post addPost(Post post);
    Post updatePost(Long id, Post post);
    Boolean deletePost(Long id);
    Post searchPost(Long id);
    List<Post> getAllPosts();
    Post getPostById(Long id);
}
