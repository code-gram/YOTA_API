package com.yash.yotaapi.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tests")
public class Tests {

    @Id
    @Column(name = "test_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(initialValue = 1, name = "id")
    private Long id;

    private String testTitle;

    private String testDescription;

    private String testInstruction;

    private String action;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private LocalDateTime created_at;

    private LocalDateTime modified_at;

    private String endTime;

    @ManyToMany
    private List<YotaUser> assign;

    @ManyToMany
    private List<Questions> questions;

    @OneToOne
    private Result result;

    private String testType;
    private String testName;
    private int totalQuestions;
    private int totalTime;

}
