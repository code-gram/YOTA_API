
package com.yash.yotaapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "assignTraining")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssignTraining {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assign_id")
    private Integer id;

    @DateTimeFormat(iso = ISO.DATE)
    @JsonFormat(pattern = "yyyy/mm/dd")
    private Date createdAt;

    @ManyToMany
    @JoinColumn(name = "email_id")
    private List<YotaUser> yotaUser;

    @ManyToOne
    @JoinColumn(name = "training_id")
    private Trainings trainings;
}