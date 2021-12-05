package com.example.optikafiness.service.impl;

import com.example.optikafiness.model.entity.*;
import com.example.optikafiness.model.entity.binding.GlassesOfferAddBindingModel;
import com.example.optikafiness.model.entity.enums.CategoryEnum;
import com.example.optikafiness.model.entity.enums.UserRole;
import com.example.optikafiness.model.entity.service.GlassesOfferAddServiceModel;
import com.example.optikafiness.model.entity.service.GlassesOfferUpdateServiceModel;
import com.example.optikafiness.model.entity.view.GlassesOfferDetailedView;
import com.example.optikafiness.model.entity.view.GlassesSummaryView;
import com.example.optikafiness.repository.BrandRepository;
import com.example.optikafiness.repository.GlassesOfferRepository;
import com.example.optikafiness.repository.ModelRepository;
import com.example.optikafiness.repository.UserRepository;
import com.example.optikafiness.service.GlassesOfferService;
import com.example.optikafiness.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GlassesOfferServiceImpl implements GlassesOfferService {

    private final GlassesOfferRepository glassesOfferRepository;
    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;

    private final BrandRepository brandRepository;

    public GlassesOfferServiceImpl(GlassesOfferRepository glassesOfferRepository, ModelRepository modelRepository, ModelMapper modelMapper,  BrandRepository brandRepository) {
        this.glassesOfferRepository = glassesOfferRepository;
        this.modelRepository = modelRepository;
        this.modelMapper = modelMapper;
        this.brandRepository = brandRepository;
    }

    @Override
    public void initializeGlassesOffers() {
        BrandEntity morani = brandRepository.findByName("Morani").orElse(null);
        BrandEntity giovanni=brandRepository.findByName("Giovanni").orElse(null);
        if (glassesOfferRepository.count()==0){
            GlassesOfferEntity offer1=new GlassesOfferEntity();

            offer1.setModel(modelRepository.findById(1L).orElse(null));
            offer1.setCategory(CategoryEnum.GLASS);
            offer1.setYearOfCreation(2019);
            offer1.setBrand(morani);
            offer1.setImageUrl("/images/image/cool-sunglasses-isolated-white-background-top-view.jpg");
            offer1.setColor("Black");
            offer1.setDescription("Very good looking");
            offer1.setPrice(99.99);

            GlassesOfferEntity offer2=new GlassesOfferEntity();
            offer2.setModel(modelRepository.findById(2L).orElse(null));
            offer2.setCategory(CategoryEnum.METAL);
            offer2.setBrand(giovanni);
            offer2.setColor("Black");
            offer2.setYearOfCreation(2020);
            offer2.setImageUrl("/images/image/eyeglasses-wear.jpg");
            offer2.setDescription("Amazingly good looking");
            offer2.setPrice(100.99);

            glassesOfferRepository.saveAll(List.of(offer1,offer2));

        }
    }

    @Override
    public List<GlassesSummaryView> getAllGlasses() {

        return glassesOfferRepository.findAll().stream().map(this::map).collect(Collectors.toList());
    }

    @Override
    public GlassesOfferDetailedView findById(Long id) {
        return this.glassesOfferRepository.findById(id).map(this::mapDetailedView).get();
    }

    @Override
    public void deleteOffer(Long id) {
        glassesOfferRepository.deleteById(id);
    }

    @Override
    public void updateOffer(GlassesOfferUpdateServiceModel serviceModel) {
        GlassesOfferEntity glassesOfferEntity=glassesOfferRepository.findById(serviceModel.getId())
                .orElseThrow(()->new ObjectNotFoundException("Offer with id:"+serviceModel.getId()+"not found!"));
        glassesOfferEntity.setCategory(serviceModel.getCategory());
        glassesOfferEntity.setPrice(serviceModel.getPrice());
        glassesOfferEntity.setYearOfCreation(serviceModel.getYearOfCreation());
        glassesOfferEntity.setImageUrl(serviceModel.getImageUrl());
        glassesOfferEntity.setColor(serviceModel.getColor());
        glassesOfferEntity.setDescription(serviceModel.getDescription());


        glassesOfferRepository.save(glassesOfferEntity);
    }

    @Override
    public GlassesOfferAddServiceModel addOffer(GlassesOfferAddBindingModel glassesOfferAddBindModel) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        GlassesOfferAddServiceModel glassesOfferAddServiceModel=modelMapper.map(glassesOfferAddBindModel,GlassesOfferAddServiceModel.class);

        Optional<BrandEntity> brandEntity = brandRepository.findByName(glassesOfferAddBindModel.getBrand().getName());
        BrandEntity newBrandEntity = null;
        if(brandEntity.isEmpty()){
            newBrandEntity = new BrandEntity();
            newBrandEntity.setName(glassesOfferAddBindModel.getBrand().getName());
            newBrandEntity = brandRepository.save(newBrandEntity);
        }
        else {
            newBrandEntity = brandEntity.get();
        }

        GlassesOfferEntity newGlassesOffer=modelMapper.map(glassesOfferAddServiceModel,GlassesOfferEntity.class);
        ModelEntity model=modelRepository.getById(glassesOfferAddBindModel.getModel().getId());
        newGlassesOffer.setModel(model);
        newGlassesOffer.setBrand(newBrandEntity);
        newGlassesOffer.setCategory(glassesOfferAddServiceModel.getCategory());
        newGlassesOffer.setCreated(Instant.now());
        GlassesOfferEntity savedOffer=glassesOfferRepository.save(newGlassesOffer);
        return modelMapper.map(savedOffer,GlassesOfferAddServiceModel.class);
    }




    private GlassesSummaryView map(GlassesOfferEntity glassesOfferEntity){
        GlassesSummaryView glassesSummaryView =this.modelMapper.map(glassesOfferEntity, GlassesSummaryView.class);

        glassesSummaryView.setBrand(glassesOfferEntity.getBrand().getName());
        glassesSummaryView.setPrice(glassesOfferEntity.getPrice().intValue());
        glassesSummaryView.setModel(glassesOfferEntity.getModel().getName());
        glassesSummaryView.setImageUrl(glassesOfferEntity.getImageUrl());

        return glassesSummaryView;
    }
    private GlassesOfferDetailedView mapDetailedView(GlassesOfferEntity glassesOfferEntity) {
        GlassesOfferDetailedView glassesView = this.modelMapper.map(glassesOfferEntity, GlassesOfferDetailedView.class);
        glassesView.setModel(glassesOfferEntity.getModel().getName());
        glassesView.setCategory(glassesOfferEntity.getCategory());
        glassesView.setBrand(glassesOfferEntity.getBrand());
        glassesView.setColor(glassesOfferEntity.getColor());
        glassesView.setDescription(glassesOfferEntity.getDescription());
        glassesView.setImageUrl(glassesOfferEntity.getImageUrl());
        glassesView.setPrice(glassesOfferEntity.getPrice());
        glassesView.setYearOfCreation(glassesOfferEntity.getYearOfCreation());
        return glassesView;
    }
}
