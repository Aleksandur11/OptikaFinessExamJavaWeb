package com.example.optikafiness.service;

import com.example.optikafiness.model.entity.view.GlassesBrandViewModel;
import com.example.optikafiness.model.entity.view.LensesBrandViewModel;

import java.util.List;

public interface BrandService {
    void initializeBrand();

    List<GlassesBrandViewModel> getAllGlassesBrands();

}
