package com.yash.yotaapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
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

    private String type;

    private String status;

    private Date startDate;
    private Date endDate;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date createdAt;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date modifiedAt;

    private String startTime;
    private String endTime;

    private int totalQuestions;
    private int totalTime;


    @ManyToMany
    private List<YotaUser> assign;
    @ManyToMany
    private List<Questions> questions;
    @OneToOne
    private Result result;

    @PrePersist
    public void created_At() {
        this.createdAt = new Date();
    }

    @PreUpdate
    public void modified_At() {
        this.modifiedAt = new Date();
    }

}
