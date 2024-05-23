package com.yash.yotaapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    private String instruction;
    private String type;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date endDate;
    private String startTime;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date createdAt;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date modifiedAt;
    private Integer totalQuestions;
    private Integer totalTime;

    @PrePersist
    public void created_At() {
        this.createdAt = new Date();
    }

    @PreUpdate
    public void modified_At() {
        this.modifiedAt = new Date();
    }
}
