package com.yash.yotaapi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Table(name = "Unit_Master")
public class UnitMaster {

    /**
     * id of Unit master for unique identification.This is going to be Primary Key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    /**
     * It represents name of unit master.
     */
    @Column(name = "name")
    private String name;

}
