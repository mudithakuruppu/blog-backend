package edu.wixze.com.service.impl;

import edu.wixze.com.dto.Post;
import edu.wixze.com.entity.CategoryEntity;
import edu.wixze.com.entity.PostEntity;
import edu.wixze.com.repository.PostRepository;
import edu.wixze.com.service.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import edu.wixze.com.repository.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public Post addPost(Post post) {
        if(post.getCategory() == null || post.getCategory().getId() == null) {
            throw new IllegalArgumentException("Post must have a valid category");
        }

        CategoryEntity category = categoryRepository.findById(post.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        PostEntity entity = modelMapper.map(post, PostEntity.class);
        entity.setCategory(category);

        PostEntity saved = postRepository.save(entity);
        return convertToDto(saved);
    }

    @Override
    public Post updatePost(Long id, Post post) {
        PostEntity existing = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        modelMapper.map(post, existing);

        if (post.getCategory() != null) {
            categoryRepository.findById(post.getCategory().getId())
                    .ifPresent(existing::setCategory);
        }

        PostEntity updated = postRepository.save(existing);
        return convertToDto(updated);
    }

    @Override
    public Boolean deletePost(Long id) {
        postRepository.deleteById(id);
        return true;
    }

    @Override
    public Post searchPost(Long id) {
        return postRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    @Override
    public List<Post> getAllPosts() {
        List<PostEntity> postEntities = postRepository.findAll();
        return postEntities.stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(
                        () -> new RuntimeException("Post not found"));
    }


    private Post convertToDto(PostEntity entity) {
        return Post.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .excerpt(entity.getExcerpt())
                .image(entity.getImage())
                .author(entity.getAuthor())
                .status(entity.getStatus())
                .views(entity.getViews())
                .dateCreated(entity.getDateCreated())
                .category(Post.Category.builder()
                        .id(entity.getCategory().getId())
                        .name(entity.getCategory().getName())
                        .slug(entity.getCategory().getSlug())
                        .build())
                .build();
    }

}
