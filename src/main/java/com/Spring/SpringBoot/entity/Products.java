package com.Spring.SpringBoot.entity;

import javax.persistence.*;

@Entity
@Table(name="Products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pr_id")
    private long Pid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_id",referencedColumnName = "cat_id")
    private Category category;

    @Column(name="pr_img")
    private String Pimg;

    @Column(name="pr_img_nm")
    private String PimgName;

    @Column(name="pr_name")
    private String Pname;

    @Column(name="pr_brand")
    private String Pbrand;

    @Column(name="pr_madein")
    private String Pmadein;

    @Column(name="pr_desc")
    private String Pdesc;

    @Column(name="pr_price")
    private long Pprice;

    public Products() {
    }

    public long getPid() {
        return Pid;
    }

    public void setPid(long pid) {
        Pid = pid;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getPimg() {
        return Pimg;
    }

    public void setPimg(String pimg) {
        Pimg = pimg;
    }

    public String getPimgName() {
        return PimgName;
    }

    public void setPimgName(String pimgName) {
        PimgName = pimgName;
    }

    public String getPname() {
        return Pname;
    }

    public void setPname(String pname) {
        Pname = pname;
    }

    public String getPbrand() {
        return Pbrand;
    }

    public void setPbrand(String pbrand) {
        Pbrand = pbrand;
    }

    public String getPmadein() {
        return Pmadein;
    }

    public void setPmadein(String pmadein) {
        Pmadein = pmadein;
    }

    public String getPdesc() {
        return Pdesc;
    }

    public void setPdesc(String pdesc) {
        Pdesc = pdesc;
    }

    public long getPprice() {
        return Pprice;
    }

    public void setPprice(long pprice) {
        Pprice = pprice;
    }

    public Products(long pid, Category category, String pimg, String pname, String pbrand, String pmadein, String pdesc, long pprice) {
        Pid = pid;
        this.category = category;
        Pimg = pimg;
        Pname = pname;
        Pbrand = pbrand;
        Pmadein = pmadein;
        Pdesc = pdesc;
        Pprice = pprice;
    }
}
