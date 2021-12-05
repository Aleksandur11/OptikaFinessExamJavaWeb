package com.example.optikafiness.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "lenses_brands")
public class LensesBrandEntity extends BaseEntity{

    private String name;
    private List<LensesOfferEntity> lenses;

    public LensesBrandEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "brand", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    public List<LensesOfferEntity> getLenses() {
        return lenses;
    }

    public void setLenses(List<LensesOfferEntity> lenses) {
        this.lenses = lenses;
    }
}
