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
@Table(name = "user_training_test")
public class UserTrainingTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_training_test_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "emp_id", referencedColumnName = "emp_id")
    private YotaUser user;

    @ManyToOne
    @JoinColumn(name = "training_id", referencedColumnName = "id")
    private Trainings trainings;

    @ManyToOne
    @JoinColumn(name = "test_id", referencedColumnName = "test_id")
    private Tests test;


}
