package com.yash.yotaapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yash.yotaapi.constants.QuestionLevelTypes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Project Name - YOTA_NEW
 * <p>
 * IDE Used - IntelliJ IDEA
 *
 * @author - yashr
 * @since - 22-04-2024
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "question_bank")
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ques_id", nullable = false)
    private Long id;

    @Column(name = "question_title" )
    @Lob
    private String questionTitle;

    @Column(name = "correct_answer")
    @Lob
    private String correctAnswer;

    @Column(name = "option_a")
    @Lob
    private String option_A;

    @Column(name = "option_b")
    @Lob
    private String option_B;

    @Column(name = "option_c")
    @Lob
    private String option_C;

    @Column(name = "option_d")
    @Lob
    private String option_D;

    @Enumerated(EnumType.STRING)
    @Column(name = "question_level")
    private QuestionLevelTypes questionLevel;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date created_At;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date updated_At;

    @PrePersist
    public void onCreate() {
        this.created_At = new Date();
    }

    @PreUpdate
    public void onUpdate() {
        this.updated_At = new Date();
    }
}
