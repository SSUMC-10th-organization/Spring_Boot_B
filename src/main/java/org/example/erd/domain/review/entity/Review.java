package org.example.erd.domain.review.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.erd.domain.member.entity.Member;
import org.example.erd.domain.store.entity.Store;
import org.example.erd.global.common.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "review")
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "score", nullable = false)
    private Float score;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY) //Cascade 안 써도 됨, 리뷰가 삭제되어도 멤버 id는 존재함
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY) //Cascade 안 써도 됨, 리뷰가 삭제되어도 가게는 존재함
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "review", cascade = CascadeType.REMOVE)
    private List<Reply> replyList = new ArrayList<>();

    @OneToMany(mappedBy = "review", cascade = CascadeType.REMOVE)
    private List<ReviewPhoto> reviewPhotoList = new ArrayList<>();





}
