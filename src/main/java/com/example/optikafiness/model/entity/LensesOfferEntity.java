package com.example.optikafiness.model.entity;


import com.example.optikafiness.model.entity.enums.LensesCategoryEnum;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name = "lenses")
public class LensesOfferEntity extends BaseEntity{

    private ModelEntity model;
    private LensesCategoryEnum category;
    private Integer yearOfCreation;
    private LensesBrandEntity brand;
    private String description;
    private String imageUrl;
    private Double price;
    private String color;

    public LensesOfferEntity() {
    }
   @Enumerated(EnumType.STRING)
    public LensesCategoryEnum getCategory() {
        return category;
    }

    public void setCategory(LensesCategoryEnum category) {
        this.category = category;
    }

    @ManyToOne
    public ModelEntity getModel() {
        return model;
    }

    public void setModel(ModelEntity model) {
        this.model = model;
    }
    @Column(nullable = false)
    @Min(1999)
    public Integer getYearOfCreation() {
        return yearOfCreation;
    }

    public void setYearOfCreation(Integer yearOfCreation) {
        this.yearOfCreation = yearOfCreation;
    }
    @Column(nullable = false)
    @Size(min = 5)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Column(nullable = false,columnDefinition = "TEXT")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    @Column(nullable = false)
    @Positive
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Column(nullable = false)
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    public LensesBrandEntity getBrand() {
        return brand;
    }

    public void setBrand(LensesBrandEntity brand) {
        this.brand = brand;
    }

}
