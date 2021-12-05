package com.example.optikafiness.service.impl;

import com.example.optikafiness.model.entity.*;
import com.example.optikafiness.model.entity.view.GlassesBrandViewModel;
import com.example.optikafiness.model.entity.view.LensesBrandViewModel;
import com.example.optikafiness.model.entity.view.ModelViewModel;
import com.example.optikafiness.repository.LensesBrandRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class LensesBrandServiceImplTest {
    private LensesBrandServiceImpl testService;

    @Mock
    private ModelMapper mockModelMapper;
    @Mock
    private LensesBrandRepository mockedLensesBrandRepository;

    private LensesBrandEntity brand,brand1;

    private List<LensesBrandEntity> allLensesBrands;

    private LensesOfferEntity lenses,lenses1;
    private ModelEntity model;

    @BeforeEach
    void init(){
        testService=new LensesBrandServiceImpl(mockedLensesBrandRepository,mockModelMapper);
        brand=new LensesBrandEntity();
        brand1=new LensesBrandEntity();
        brand.setName("Giovanni");
        brand1.setName("Roberto");

        lenses=new LensesOfferEntity();
        lenses1=new LensesOfferEntity();
        model=new ModelEntity();
        model.setName("Gosho");

        lenses.setBrand(brand);
        lenses.setModel(model);
        lenses1.setBrand(brand1);
        lenses1.setModel(model);
        brand.setLenses(List.of(lenses,lenses1));
       brand1.setLenses(List.of(lenses,lenses1));
        allLensesBrands=new ArrayList<>();
        allLensesBrands.add(brand);
        allLensesBrands.add(brand1);

    }
    @Test
    void testInitialize(){
        testService.initializeLensesBrand();
        Mockito.when(mockedLensesBrandRepository.findAll()).thenReturn(List.of(brand));

        var actual=mockedLensesBrandRepository.findAll().stream().map(LensesBrandEntity::getName).collect(Collectors.joining(","));
        var expected=brand.getName();
        Assertions.assertEquals(expected,actual);
    }
    @Test
    void getAllLensesBrandsTest(){

        ModelViewModel modelViewModel=new ModelViewModel();
        ModelViewModel modelViewModel1=new ModelViewModel();
        Mockito.when(mockedLensesBrandRepository.findAll()).thenReturn(List.of(brand,brand1));

        Mockito.when(mockModelMapper.map(lenses,ModelViewModel.class)).thenReturn(modelViewModel);
        Mockito.when(mockModelMapper.map(lenses1,ModelViewModel.class)).thenReturn(modelViewModel1);

        var expected=testService.getAllLensesBrands().stream().map(LensesBrandViewModel::getName).collect(Collectors.toList());
        var actual=allLensesBrands.stream().map(LensesBrandEntity::getName).collect(Collectors.toList());

        Assertions.assertEquals(expected,actual);
    }
}
