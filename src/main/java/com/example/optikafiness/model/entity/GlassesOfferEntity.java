package com.example.optikafiness.model.entity;

import com.example.optikafiness.model.entity.enums.CategoryEnum;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name = "glasses")
public class GlassesOfferEntity extends BaseEntity{


    private ModelEntity model;
    private CategoryEnum category;
    private Integer yearOfCreation;
    private BrandEntity brand;
    private String description;
    private String imageUrl;
    private Double price;
    private String color;

    public GlassesOfferEntity() {
    }
@Enumerated(EnumType.STRING)
    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
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


    @ManyToOne
    public BrandEntity getBrand() {
        return brand;
    }

    public GlassesOfferEntity setBrand(BrandEntity brand) {
        this.brand = brand;
        return this;
    }
}
