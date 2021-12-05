package com.example.optikafiness.model.entity;
import com.example.optikafiness.model.entity.enums.CategoryEnum;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Table(name = "models")
public class ModelEntity extends BaseEntity{

    private String name;


    public ModelEntity() {
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
