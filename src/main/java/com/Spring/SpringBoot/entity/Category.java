package com.Spring.SpringBoot.entity;

import javax.persistence.*;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cat_id")
    private long Cid;

    @Column(name="cat_name")
    private String Cname;
}
