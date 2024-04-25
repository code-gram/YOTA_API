package com.yash.yotaapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

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
@Table(name = "category_management")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Long id;

    @Column(name = "category_name")
    private String name;

    @Transient
    private Integer questionCountUnderCategory = 0;

    @ManyToOne
    @JoinColumn(name = "tech_id", referencedColumnName = "tehnology_id")
    private Technology technology;

    @OneToMany(mappedBy = "category")
    private List<Questions> questions;
}
