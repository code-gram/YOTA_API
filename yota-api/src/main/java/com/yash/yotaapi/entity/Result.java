package com.yash.yotaapi.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "result")
public class Result {

    @Id
    @Column(name = "resultId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(initialValue = 1, name = "id")
    private Long id;

    private String result;
}
