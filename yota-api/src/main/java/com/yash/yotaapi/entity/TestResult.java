package com.yash.yotaapi.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "test_results")
public class TestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "emp_id")
    private YotaUser user;

    @ManyToOne
    @JoinColumn(name = "test_id", referencedColumnName = "test_id")
    private Tests test;

    @Column(name = "result")
    private Long result;

    private String startTime;

    private String endTime;

    private String timeTaken;
    
    private String  testStatus;
}
