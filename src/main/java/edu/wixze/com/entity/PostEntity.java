package edu.wixze.com.entity;

import edu.wixze.com.enums.PostStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is mandatory")
    @Size(min = 5, max = 255, message = "Title must be 5-255 characters")
    @Column(nullable = false, length = 255)
    private String title;

    @NotBlank(message = "Content is mandatory")
    @Size(min = 100, message = "Content must be at least 100 characters")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @NotBlank(message = "Excerpt is mandatory")
    @Size(max = 300, message = "Excerpt must be ≤ 300 characters")
    @Column(nullable = false, length = 300)
    private String excerpt;

    @Size(max = 255)
    @Column(length = 255)
    private String image;

    @NotBlank(message = "Author is mandatory")
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String author;

    @NotNull(message = "Status is mandatory")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PostStatus status;

    @NotNull(message = "Category is mandatory")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CategoryEntity category;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created", updatable = false)
    private Date dateCreated;

    @Column(nullable = false)
    private Long views = 0L;

}