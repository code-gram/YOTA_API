package com.yash.yotaapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_test_answers")
public class UserTestAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_test_answer_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "test_id", referencedColumnName = "test_id")
    private Tests test;

    @ManyToOne
    @JoinColumn(name = "emp_id", referencedColumnName = "emp_id")
    private YotaUser user;

    @ManyToOne(cascade = CascadeType.ALL)  // Add cascade option if appropriate
    @JoinColumn(name = "ques_id", referencedColumnName = "ques_id")
    private Questions question;

    @Column(name = "selected_option")
    private String selectedOption;
}
