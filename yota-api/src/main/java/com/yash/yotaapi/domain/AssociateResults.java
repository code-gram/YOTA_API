package com.yash.yotaapi.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class AssociateResults {

    /**
     * ID is a primary key and it is generated automatically.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Associate_id of associate.
     */
    @ManyToOne
    @JoinColumn(name ="associate_id")
    private long associate_id;

    /**
     * Test_id of associate.
     */
    @ManyToOne
    @JoinColumn(name ="test_id")
    private long test_id;

    /**
     * score of associate.
     */
    private double score;

}


