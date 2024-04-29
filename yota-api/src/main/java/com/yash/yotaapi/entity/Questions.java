package com.yash.yotaapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yash.yotaapi.constants.QuestionLevelTypes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
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

    @Column(name = "question_title")
    private String questionTitle;

    @Column(name = "correct_answer")
    private String correctAnswer;

    @Column(name = "option_a")
    private String option_A;

    @Column(name = "option_b")
    private String option_B;

    @Column(name = "option_c")
    private String option_C;

    @Column(name = "option_d")
    private String option_D;

    @Enumerated(EnumType.STRING)
    @Column(name = "question_level")
    private QuestionLevelTypes questionLevel;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date created_At;

    @JsonFormat(pattern = "yyyy-MM-dd")
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
