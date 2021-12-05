package com.example.optikafiness.model.entity;

import java.time.Instant;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "brands")
public class BrandEntity extends BaseEntity {

    private String name;

    private List<GlassesOfferEntity> glasses;

    public BrandEntity() {
    }

    public String getName() {
        return name;
    }

    public BrandEntity setName(String name) {
        this.name = name;
        return this;
    }
    @OneToMany(mappedBy = "brand",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    public List<GlassesOfferEntity> getGlasses() {
        return glasses;
    }

    public void setGlasses(List<GlassesOfferEntity> glasses) {
        this.glasses = glasses;
    }

}
