package org.example.erd.domain.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.erd.domain.member.entity.mapping.MemberTerm;
import org.example.erd.global.common.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "term")
public class Term extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "isOptional", nullable = false)
    private Boolean isOptional;

    @OneToMany(mappedBy = "term",cascade = CascadeType.REMOVE)
    private List<MemberTerm> memberTermList = new ArrayList<>();
}
