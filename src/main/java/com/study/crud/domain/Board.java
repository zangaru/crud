package com.study.crud.domain;

import com.study.crud.dto.UpdateFormDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id", nullable = false)
    private Long id;

    @Column(name = "board_title", nullable = false)
    private String title;

    @Column(name = "board_content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "board_created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "board_updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "board_admin_views", nullable = false)
    private int adminViews;

    @Column(name = "board_user_views", nullable = false)
    private int userViews;

    @Column(name = "board_likes", nullable = false)
    private int likes;

    @Enumerated(EnumType.STRING)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;


    public void countAdmin() {
        this.adminViews++;
    }

    public void countUser() {
        this.userViews++;
    }

    public void update(UpdateFormDTO updateFormDTO) {
        this.title = updateFormDTO.getTitle();
        this.content = updateFormDTO.getContent();
        this.updatedAt = LocalDateTime.now();
    }
}
