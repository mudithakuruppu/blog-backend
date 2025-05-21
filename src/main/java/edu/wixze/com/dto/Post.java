package edu.wixze.com.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import edu.wixze.com.enums.PostStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private Long id;

    @NotBlank(message = "Title is mandatory")
    @Size(min = 5, max = 255, message = "Title must be between 5-255 characters")
    private String title;

    @NotBlank(message = "Content is mandatory")
    @Size(min = 100, message = "Content must be at least 100 characters")
    private String content;

    @NotBlank(message = "Excerpt is mandatory")
    @Size(max = 300, message = "Excerpt must be less than 300 characters")
    private String excerpt;

    private String image;

    @NotBlank(message = "Author is mandatory")
    private String author;

    @NotNull(message = "Status is mandatory")
    private PostStatus status;

    @NotNull(message = "Category is mandatory")
    private Category category;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateCreated;

    private Long views;

    // Nested Category DTO for proper JSON structure
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Category {
        @NotNull(message = "Category ID is mandatory")
        private Long id;

        private String name;
        private String slug;
    }
}