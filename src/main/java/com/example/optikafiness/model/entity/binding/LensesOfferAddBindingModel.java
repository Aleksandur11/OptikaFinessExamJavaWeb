package com.example.optikafiness.model.entity.binding;

import com.example.optikafiness.model.entity.BrandEntity;
import com.example.optikafiness.model.entity.LensesBrandEntity;
import com.example.optikafiness.model.entity.ModelEntity;
import com.example.optikafiness.model.entity.enums.CategoryEnum;
import com.example.optikafiness.model.entity.enums.LensesCategoryEnum;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LensesOfferAddBindingModel {
    private Long id;
    private Long modelId;
    @NotNull
    private ModelEntity model;
    @NotNull
    private LensesCategoryEnum category;
    @Min(1985)
    private Integer yearOfCreation;
    @NotNull
    private LensesBrandEntity brand;
    @Size(min = 5)
    private String description;
    @NotBlank
    private String imageUrl;
    private Double price;
    @Size(min = 3)
    private String color;

    public LensesOfferAddBindingModel() {
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public ModelEntity getModel() {
        return model;
    }

    public void setModel(ModelEntity model) {
        this.model = model;
    }

    public LensesCategoryEnum getCategory() {
        return category;
    }

    public void setCategory(LensesCategoryEnum category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
