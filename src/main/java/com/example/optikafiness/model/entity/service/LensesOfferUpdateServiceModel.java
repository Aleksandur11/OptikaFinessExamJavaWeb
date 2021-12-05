package com.example.optikafiness.model.entity.service;

import com.example.optikafiness.model.entity.ModelEntity;
import com.example.optikafiness.model.entity.enums.CategoryEnum;
import com.example.optikafiness.model.entity.enums.LensesCategoryEnum;

public class LensesOfferUpdateServiceModel {
    private Long id;
    private LensesCategoryEnum category;

    private Integer yearOfCreation;

    private String description;

    private String imageUrl;
    private Double price;
    private String color;
    public LensesOfferUpdateServiceModel() {
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

    public Integer getYearOfCreation() {
        return yearOfCreation;
    }

    public void setYearOfCreation(Integer yearOfCreation) {
        this.yearOfCreation = yearOfCreation;
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
