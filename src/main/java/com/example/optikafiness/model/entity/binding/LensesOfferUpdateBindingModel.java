package com.example.optikafiness.model.entity.binding;

import com.example.optikafiness.model.entity.ModelEntity;
import com.example.optikafiness.model.entity.enums.CategoryEnum;
import com.example.optikafiness.model.entity.enums.LensesCategoryEnum;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LensesOfferUpdateBindingModel {

    private Long id;
    @NotNull
    private LensesCategoryEnum category;
    @Min(1985)
    private Integer yearOfCreation;
    @Size(min = 5)
    private String description;
    @NotBlank
    private String imageUrl;
    private Double price;
    @Size(min = 3)
    private String color;

    public LensesOfferUpdateBindingModel() {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
