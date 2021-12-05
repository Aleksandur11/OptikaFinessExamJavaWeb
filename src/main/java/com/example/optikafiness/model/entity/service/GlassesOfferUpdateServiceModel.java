package com.example.optikafiness.model.entity.service;

import com.example.optikafiness.model.entity.BrandEntity;
import com.example.optikafiness.model.entity.ModelEntity;
import com.example.optikafiness.model.entity.enums.CategoryEnum;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class GlassesOfferUpdateServiceModel {
    private Long id;
    private CategoryEnum category;

    private Integer yearOfCreation;

    private String description;

    private String imageUrl;
    private Double price;
    private String color;

    public GlassesOfferUpdateServiceModel() {
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
