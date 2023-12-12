package com.kfhstu.furn.bean;


import org.hibernate.validator.constraints.Range;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Optional;

public class Furn implements Serializable {
    private Integer id;

    @NotEmpty(message = "请输入名字")
    private String name;

    @NotEmpty(message = "请输入厂商")
    private String maker;

    @NotNull(message = "请输入单价")
    @Range(min = 0,message = "单价不能为负数")
    private BigDecimal price;

    @NotNull(message = "请输入销量")
    @Range(min = 0,message = "销量不能为负数")
    private Integer sales;

    @NotNull(message = "请输入库存")
    @Range(min = 0,message = "库存不能为负数")
    private Integer stock;

    //默认值
    private String imgPath = "assets/image/product-image/6.jpg";

    public Furn() {
    }

    public Furn(Integer id, String name, String maker, BigDecimal price, Integer sales, Integer stock, String imgPath) {
        this.id = id;
        this.name = name;
        this.maker = maker;
        this.price = price;
        this.sales = sales;
        this.stock = stock;
        // set default value
        if (StringUtils.hasText(imgPath)) {
            this.imgPath = imgPath;
        }

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker == null ? null : maker.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = this.imgPath = Optional.ofNullable(imgPath).orElse("assets/image/product-image/6.jpg");
    }
}