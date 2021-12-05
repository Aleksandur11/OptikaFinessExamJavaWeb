package com.example.optikafiness.model.entity.view;

import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

public class GlassesBrandViewModel {
    private String name;
    private List<ModelViewModel> models=new ArrayList<>();

    public GlassesBrandViewModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ModelViewModel> getModels() {
        return models;
    }

    public void setModels(List<ModelViewModel> models) {
        this.models = models;
    }
    public GlassesBrandViewModel addModel(ModelViewModel model) {
        if (this.models == null) {
            this.models = new ArrayList<>();
        }
        this.models.add(model);
        return this;
    }
}
