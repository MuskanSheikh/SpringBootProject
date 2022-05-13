package com.Spring.SpringBoot.entity;

import javax.persistence.*;

@Entity
@Table(name="Products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pr_id")
    private long pr_id;

    @Column(name="pr_img", columnDefinition ="MEDIUMBLOB")
    private String pr_img;

    @Column(name="pr_name")
    private String pr_name;

    @Column(name="pr_brand")
    private String pr_brand;

    @Column(name="pr_madein")
    private String pr_madein;

    @Column(name="pr_desc")
    private String pr_desc;

    @Column(name="pr_price")
    private long pr_price;

    public Products() {
    }

    public long getPr_id() {
        return pr_id;
    }

    public void setPr_id(long pr_id) {
        this.pr_id = pr_id;
    }

    public String getPr_name() {
        return pr_name;
    }

    public void setPr_name(String pr_name) {
        this.pr_name = pr_name;
    }

    public String getPr_img() {
        return pr_img;
    }

    public void setPr_img(String pr_img) {
        this.pr_img = pr_img;
    }

    public String getPr_brand() {
        return pr_brand;
    }

    public void setPr_brand(String pr_brand) {
        this.pr_brand = pr_brand;
    }

    public String getPr_madein() {
        return pr_madein;
    }

    public void setPr_madein(String pr_madein) {
        this.pr_madein = pr_madein;
    }

    public String getPr_desc() {
        return pr_desc;
    }

    public void setPr_desc(String pr_desc) {
        this.pr_desc = pr_desc;
    }

    public long getPr_price() {
        return pr_price;
    }

    public void setPr_price(long pr_price) {
        this.pr_price = pr_price;
    }

    public Products(long pr_id, String pr_name, String pr_brand, String pr_madein, String pr_desc, long pr_price) {
        this.pr_id = pr_id;
        this.pr_name = pr_name;
        this.pr_brand = pr_brand;
        this.pr_madein = pr_madein;
        this.pr_desc = pr_desc;
        this.pr_price = pr_price;
    }
}
