package com.yash.yotaapi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * This UnitMaster Management model will work as a data transfer object.
 * Field validation will be performed here using jpa annotations.
 *
 * @author pravin.navarkar
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Unit {

    /**
     * id of Unit master for unique identification.This is going to be Primary Key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    /**
     * It represents name of unit master and checks whether name is empty or null.
     */
    @NotEmpty
    private String unitName;

    private String shortDescription;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @CreatedDate
    private Date createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @LastModifiedDate
    private Date updatedAt;

    /** Pre-persist action to set the created date. */
    @PrePersist
    public void onCreate() {
        this.createdAt = new Date();
    }

    /** Pre-update action to set the updated date. */
    @PreUpdate
    public void onUpdate() {
        this.updatedAt = new Date();
    }
}
