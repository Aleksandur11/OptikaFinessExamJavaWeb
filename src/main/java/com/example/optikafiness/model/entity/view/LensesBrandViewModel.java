package com.example.optikafiness.model.entity.view;

import java.util.ArrayList;
import java.util.List;

public class LensesBrandViewModel {
    private String name;
    private List<ModelViewModel> models=new ArrayList<>();

    public LensesBrandViewModel() {
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
    public LensesBrandViewModel addModel(ModelViewModel model) {
        if (this.models == null) {
            this.models = new ArrayList<>();
        }
        this.models.add(model);
        return this;
    }
}
