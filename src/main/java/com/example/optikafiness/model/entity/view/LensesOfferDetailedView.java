package com.example.optikafiness.model.entity.view;

import com.example.optikafiness.model.entity.BrandEntity;
import com.example.optikafiness.model.entity.LensesBrandEntity;
import com.example.optikafiness.model.entity.enums.CategoryEnum;
import com.example.optikafiness.model.entity.enums.LensesCategoryEnum;

public class LensesOfferDetailedView {
    private String name;
    private Integer yearOfCreation;
    private LensesBrandEntity brand;
    private String description;
    private String imageUrl;
    private Double price;
    private LensesCategoryEnum category;
    private String color;
    private Long id;
    private String model;

    public LensesOfferDetailedView() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYearOfCreation() {
        return yearOfCreation;
    }

    public void setYearOfCreation(Integer yearOfCreation) {
        this.yearOfCreation = yearOfCreation;
    }

    public LensesBrandEntity getBrand() {
        return brand;
    }

    public void setBrand(LensesBrandEntity brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LensesCategoryEnum getCategory() {
        return category;
    }

    public void setCategory(LensesCategoryEnum category) {
        this.category = category;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
