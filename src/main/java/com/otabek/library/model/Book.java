package com.otabek.library.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String author;
    @Column(columnDefinition = "TEXT")
    private String isbn;
    private String publisher;
    private Integer count;
    private Integer pageCount;
    private LocalDate publicationDate;
    @ManyToOne
    private Category category;
    @Enumerated(value = EnumType.STRING)
    private AvailabilityStatus availabilityStatus;
    @Column(columnDefinition = "TEXT")
    private String imageUrl;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public void setCount(Integer count) {
        if (count == 0){
            this.setAvailabilityStatus(AvailabilityStatus.BORROWED);
        } else {
            this.setAvailabilityStatus(AvailabilityStatus.AVAILABLE);
        }
        this.count = count;
    }
}