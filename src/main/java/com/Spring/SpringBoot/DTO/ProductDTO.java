package com.Spring.SpringBoot.DTO;

import lombok.Data;

@Data
public class ProductDTO {
    private long Pid;
    private long categoryId;
    private String PimgName;
    private String Pimg;
    private String Pname;
    private String Pbrand;
    private String Pmadein;
    private String Pdesc;
    private long Pprice;
}
