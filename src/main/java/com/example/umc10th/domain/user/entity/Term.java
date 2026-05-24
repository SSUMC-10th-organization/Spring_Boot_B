package com.example.umc10th.domain.user.entity;

import com.example.umc10th.domain.user.entity.mapping.UserTerm;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "term")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "term_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "term_name", nullable = false)
    private TermName termName;

    @OneToMany(mappedBy = "term", cascade = CascadeType.ALL)
    private List<UserTerm> userTerms = new ArrayList<>();

    public enum TermName {
        AGE, SERVICE, PRIVACY
    }
}