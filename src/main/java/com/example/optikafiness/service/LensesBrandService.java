package com.example.optikafiness.service;

import com.example.optikafiness.model.entity.view.LensesBrandViewModel;

import java.util.List;

public interface LensesBrandService {
    List<LensesBrandViewModel> getAllLensesBrands();
    void initializeLensesBrand();
}
