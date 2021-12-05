package com.example.optikafiness.model.entity.service;

import com.example.optikafiness.model.entity.BrandEntity;
import com.example.optikafiness.model.entity.LensesBrandEntity;
import com.example.optikafiness.model.entity.ModelEntity;
import com.example.optikafiness.model.entity.enums.CategoryEnum;
import com.example.optikafiness.model.entity.enums.LensesCategoryEnum;

public class LensesOfferAddServiceModel {
    private Long id;
    private Long modelId;
    private LensesCategoryEnum category;
    private Integer yearOfCreation;
    private LensesBrandEntity brand;
    private String description;
    private String imageUrl;
    private Double price;
    private String color;

    public LensesOfferAddServiceModel() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LensesCategoryEnum getCategory() {
        return category;
    }

    public void setCategory(LensesCategoryEnum category) {
        this.category = category;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
