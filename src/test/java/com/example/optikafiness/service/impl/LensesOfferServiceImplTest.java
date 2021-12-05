package com.example.optikafiness.service.impl;

import com.example.optikafiness.model.entity.GlassesOfferEntity;
import com.example.optikafiness.model.entity.LensesBrandEntity;
import com.example.optikafiness.model.entity.LensesOfferEntity;
import com.example.optikafiness.model.entity.ModelEntity;
import com.example.optikafiness.model.entity.enums.LensesCategoryEnum;
import com.example.optikafiness.model.entity.view.GlassesOfferDetailedView;
import com.example.optikafiness.model.entity.view.GlassesSummaryView;
import com.example.optikafiness.model.entity.view.LensesOfferDetailedView;
import com.example.optikafiness.model.entity.view.LensesSummaryView;
import com.example.optikafiness.repository.*;
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
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class LensesOfferServiceImplTest {
    private LensesOfferServiceImpl testService;

    @Mock
    private ModelRepository mockModelRepository;
    @Mock
    private LensesOfferRepository mockedLensesOfferRepository;
    @Mock
    private ModelMapper mockModelMapper;
    @Mock
    private  LensesBrandRepository mockLensesBrandRepository;
    private LensesOfferEntity lenses1,lenses2;
    private LensesBrandEntity brand;
    private ModelEntity model;
    private List<LensesOfferEntity> allLenses;


    @BeforeEach
    void init(){
        testService=new LensesOfferServiceImpl(mockModelRepository,mockedLensesOfferRepository,mockModelMapper,mockLensesBrandRepository);

        brand=new LensesBrandEntity();
        brand.setName("Morani");

        model=new ModelEntity();
        model.setName("Dragan");
         lenses1=new LensesOfferEntity();
         lenses2=new LensesOfferEntity();

        lenses1.setCategory(LensesCategoryEnum.ANNUAL);
        lenses1.setModel(model);
        lenses1.setYearOfCreation(2019);
        lenses1.setBrand(brand);
        lenses1.setImageUrl("/images/image/cool-sunglasses-isolated-white-background-top-view.jpg");
        lenses1.setColor("Black");
        lenses1.setDescription("Very good looking");
        lenses1.setPrice(99.99);
        lenses1.setId(1L);

        lenses2.setModel(model);
        lenses2.setCategory(LensesCategoryEnum.MONTHLY);
        lenses2.setYearOfCreation(2019);
        lenses2.setBrand(brand);
        lenses2.setImageUrl("/images/image/cool-sunglasses-isolated-white-background-top-view.jpg");
        lenses2.setColor("Black");
        lenses2.setDescription("Very good looking");
        lenses2.setPrice(99.99);

        allLenses=new ArrayList<>();
        allLenses.add(lenses1);
        allLenses.add(lenses2);


    }
    @Test
    void testInitialize(){
        testService.initializeLensesOffers();

        Mockito.when(mockedLensesOfferRepository.findAll()).thenReturn(List.of(lenses1));

        var actual=mockedLensesOfferRepository.findAll().stream().map(LensesOfferEntity::getColor).collect(Collectors.joining(","));
        var expected=lenses1.getColor();
        Assertions.assertEquals(expected,actual);
    }
    @Test
    void testGellAllLenses(){
       LensesSummaryView lensesSummaryView =new LensesSummaryView();
       LensesSummaryView lensesSummaryView1=new LensesSummaryView();
        Mockito.when(mockedLensesOfferRepository.findAll()).thenReturn(List.of(lenses1,lenses2));
        Mockito.when(mockModelMapper.map(lenses1,LensesSummaryView.class)).thenReturn(lensesSummaryView);
        Mockito.when(mockModelMapper.map(lenses2,LensesSummaryView.class)).thenReturn(lensesSummaryView1);

        var actual= testService.getAllLenses().stream().map(LensesSummaryView::getImageUrl).collect(Collectors.toList());

        var expected=allLenses.stream().map(LensesOfferEntity::getImageUrl).collect(Collectors.toList());
        Assertions.assertEquals(expected,actual);
    }
    @Test
    void findByIdTest(){

        LensesOfferDetailedView lensesOfferDetailedView=new LensesOfferDetailedView();
        lensesOfferDetailedView.setId(1L);

        Mockito.when(mockedLensesOfferRepository.findById(1L)).thenReturn(Optional.of(lenses1));
        Mockito.when(mockModelMapper.map(lenses1,LensesOfferDetailedView.class)).thenReturn(lensesOfferDetailedView);

        var actual=testService.findById(1L);


        Assertions.assertEquals(lensesOfferDetailedView,actual);
    }
    @Test
    void deleteByIdTest(){
        testService.deleteOffer(1L);
        Mockito.verify(mockedLensesOfferRepository,Mockito.times(1)).deleteById(1L);



    }

}
