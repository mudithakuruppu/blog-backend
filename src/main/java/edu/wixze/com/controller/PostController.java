package edu.wixze.com.controller;

import edu.wixze.com.dto.Post;
import edu.wixze.com.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/add-post")
    public void addPost(@RequestBody Post post){
        postService.addPost(post);
    }

    @PutMapping("/update-post/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post post) {
        return postService.updatePost(id, post);
    }

    @DeleteMapping("/delete-post/{id}")
    public Boolean deletePost(@PathVariable Long id){
        return postService.deletePost(id);
    }

    @GetMapping("/search-post/{id}")
    public Post searchPost(@PathVariable Long id){
        return postService.searchPost(id);
    }

    @GetMapping("/all-posts")
    public List<Post> getAllPosts(){
        return postService.getAllPosts();
    }

}
