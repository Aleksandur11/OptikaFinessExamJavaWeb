package com.example.optikafiness.service.impl;

import com.example.optikafiness.model.entity.*;
import com.example.optikafiness.model.entity.enums.UserRole;
import com.example.optikafiness.model.entity.view.GlassesBrandViewModel;
import com.example.optikafiness.model.entity.view.ModelViewModel;
import com.example.optikafiness.repository.BrandRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.ui.ModelMap;

import java.util.*;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
class BrandServiceImplTest {
    private BrandServiceImpl serviceToTest;


    private BrandEntity morani,giovanni;
    @Mock
    private BrandRepository mockBrandRepository;
    @Mock
    private ModelMapper mockModelMapper;

    private List<BrandEntity> allBrands;
    private GlassesOfferEntity glass1,glass2;
    private ModelEntity model;

    @BeforeEach
    void init(){
        serviceToTest=new BrandServiceImpl(mockBrandRepository,mockModelMapper);
        morani=new BrandEntity();
        giovanni=new BrandEntity();
        morani.setName("Morani");
        giovanni.setName("Giovanni");
        glass1=new GlassesOfferEntity();
        glass2=new GlassesOfferEntity();
        model=new ModelEntity();
        model.setName("Ivan");

        glass1.setBrand(morani);
        glass1.setModel(model);
        glass2.setBrand(giovanni);
        glass2.setModel(model);
        morani.setGlasses(List.of(glass1,glass2));
        giovanni.setGlasses(List.of(glass1,glass2));
        allBrands=new ArrayList<>();
        allBrands.add(morani);
        allBrands.add(giovanni);




    }
    @Test
    void testInitializeBrand(){
        serviceToTest.initializeBrand();
        Mockito.when(mockBrandRepository.findAll()).thenReturn(List.of(morani));
        var actual=mockBrandRepository.findAll().stream().map(BrandEntity::getName).collect(Collectors.joining(","));
        var expected="Morani";
        Assertions.assertEquals(expected,actual);
    }
    @Test
    void getAllGlassesBrandsTest(){

        ModelViewModel modelViewModel=new ModelViewModel();
        ModelViewModel modelViewModel1=new ModelViewModel();
        Mockito.when(mockBrandRepository.findAll()).thenReturn(List.of(morani,giovanni));

        Mockito.when(mockModelMapper.map(glass1,ModelViewModel.class)).thenReturn(modelViewModel);
        Mockito.when(mockModelMapper.map(glass2,ModelViewModel.class)).thenReturn(modelViewModel1);

        var expected=serviceToTest.getAllGlassesBrands().stream().map(GlassesBrandViewModel::getName).collect(Collectors.toList());
        var actual=allBrands.stream().map(BrandEntity::getName).collect(Collectors.toList());

        Assertions.assertEquals(expected,actual);
    }


}
