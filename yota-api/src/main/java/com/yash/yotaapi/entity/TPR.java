package com.yash.yotaapi.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tpr")
public class TPR {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(initialValue = 1, name = "id")
    private Long id;

    @Column(name = "emp_id")
    private Long empId;

    @Column(name = "training_id")
    private Long trainingId;

    @Column(name = "avg_percentage")
    private double avgPercentage;

    @Column(name = "test_ids")
    private String testsIds;
}