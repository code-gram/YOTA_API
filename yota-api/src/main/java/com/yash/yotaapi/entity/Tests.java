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

    @Lob
    private String testDescription;

    @Lob
    private String testInstruction;

    private String type;

    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date modifiedAt;

    private String startTime;
    private String endTime;
    private Integer durationTime;

    private Integer totalQuestions;
    private int totalTime;
    private int totalAssociateCount;
    private String testStatus;

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
