package com.example.optikafiness.service.impl;

import com.example.optikafiness.model.entity.BrandEntity;
import com.example.optikafiness.model.entity.GlassesOfferEntity;
import com.example.optikafiness.model.entity.ModelEntity;
import com.example.optikafiness.model.entity.enums.CategoryEnum;
import com.example.optikafiness.model.entity.enums.UserRole;
import com.example.optikafiness.model.entity.view.GlassesOfferDetailedView;
import com.example.optikafiness.model.entity.view.GlassesSummaryView;
import com.example.optikafiness.model.entity.view.UserView;
import com.example.optikafiness.repository.BrandRepository;
import com.example.optikafiness.repository.GlassesOfferRepository;
import com.example.optikafiness.repository.ModelRepository;
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
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class GlassesOfferServiceImplTest {
    private GlassesOfferServiceImpl testService;

    @Mock
    private GlassesOfferRepository mockedGlassesOfferRepository;
    @Mock
    private ModelMapper mockModelMapper;
    @Mock
    private ModelRepository mockModelRepository;
    @Mock
    private BrandRepository mockedBrandRepository;
    private GlassesOfferEntity glass1,glass2;
    private BrandEntity morani;
    private ModelEntity model;
   private List<GlassesOfferEntity> allGlasses;



    @BeforeEach
    void init() {
        testService = new GlassesOfferServiceImpl(mockedGlassesOfferRepository, mockModelRepository, mockModelMapper, mockedBrandRepository);
       morani=new BrandEntity();
       morani.setName("Morani");

       model=new ModelEntity();
       model.setName("Ivan");

        glass1=new GlassesOfferEntity();
        glass2=new GlassesOfferEntity();


        glass1.setDescription("Very good looking");
        glass1.setColor("Black");
        glass1.setImageUrl("/images/image/cool-sunglasses-isolated-white-background-top-view.jpg");
        glass1.setPrice(100.00);
        glass1.setBrand(morani);
        glass1.setYearOfCreation(1999);
        glass1.setModel(model);
        glass1.setCategory(CategoryEnum.METAL);
        glass1.setId(1L);


        glass2.setDescription("Very good looking");
        glass2.setColor("Black");
        glass2.setImageUrl("/images/image/cool-sunglasses-isolated-white-background-top-view.jpg");
        glass2.setPrice(100.00);
        glass2.setBrand(morani);
        glass2.setYearOfCreation(1999);
        glass2.setModel(model);
        glass2.setCategory(CategoryEnum.METAL);
    allGlasses=new ArrayList<>();
     allGlasses.add(glass1);
     allGlasses.add(glass2);


        testService.initializeGlassesOffers();
    }
    @Test
    void testInitialize(){
        Mockito.when(mockedGlassesOfferRepository.findAll()).thenReturn(List.of(glass1));

        var actual=mockedGlassesOfferRepository.findAll().stream().map(GlassesOfferEntity::getColor).collect(Collectors.joining(","));
        var expected=glass1.getColor();
        Assertions.assertEquals(expected,actual);
    }
    @Test
    void testGetAllGlasses(){
        GlassesSummaryView glassesSummaryView=new GlassesSummaryView();
        GlassesSummaryView glassesSummaryView1=new GlassesSummaryView();
        Mockito.when(mockedGlassesOfferRepository.findAll()).thenReturn(List.of(glass1,glass2));
        Mockito.when(mockModelMapper.map(glass1,GlassesSummaryView.class)).thenReturn(glassesSummaryView);
        Mockito.when(mockModelMapper.map(glass2,GlassesSummaryView.class)).thenReturn(glassesSummaryView1);

       var actual= testService.getAllGlasses().stream().map(GlassesSummaryView::getImageUrl).collect(Collectors.toList());

       var expected=allGlasses.stream().map(GlassesOfferEntity::getImageUrl).collect(Collectors.toList());
       Assertions.assertEquals(expected,actual);
    }
    @Test
    void findByIdTest(){

        GlassesOfferDetailedView glassesOfferDetailedView=new GlassesOfferDetailedView();
       glassesOfferDetailedView.setId(1L);

        Mockito.when(mockedGlassesOfferRepository.findById(1L)).thenReturn(Optional.of(glass1));
        Mockito.when(mockModelMapper.map(glass1,GlassesOfferDetailedView.class)).thenReturn(glassesOfferDetailedView);

        var actual=testService.findById(1L);


        Assertions.assertEquals(glassesOfferDetailedView,actual);
    }
    @Test
    void deleteByIdTest(){
        testService.deleteOffer(1L);
        Mockito.verify(mockedGlassesOfferRepository,Mockito.times(1)).deleteById(1L);



    }
}
