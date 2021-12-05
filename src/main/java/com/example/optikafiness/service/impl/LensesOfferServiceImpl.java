package com.example.optikafiness.service.impl;

import com.example.optikafiness.model.entity.BrandEntity;
import com.example.optikafiness.model.entity.LensesBrandEntity;
import com.example.optikafiness.model.entity.LensesOfferEntity;
import com.example.optikafiness.model.entity.ModelEntity;
import com.example.optikafiness.model.entity.binding.LensesOfferAddBindingModel;
import com.example.optikafiness.model.entity.enums.LensesCategoryEnum;
import com.example.optikafiness.model.entity.service.LensesOfferAddServiceModel;
import com.example.optikafiness.model.entity.service.LensesOfferUpdateServiceModel;
import com.example.optikafiness.model.entity.view.LensesOfferDetailedView;
import com.example.optikafiness.model.entity.view.LensesSummaryView;
import com.example.optikafiness.repository.BrandRepository;
import com.example.optikafiness.repository.LensesBrandRepository;
import com.example.optikafiness.repository.LensesOfferRepository;
import com.example.optikafiness.repository.ModelRepository;
import com.example.optikafiness.service.LensesOfferService;
import com.example.optikafiness.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LensesOfferServiceImpl implements LensesOfferService {

    private final ModelRepository modelRepository;
    private final LensesOfferRepository lensesOfferRepository;
    private final ModelMapper modelMapper;

    private final LensesBrandRepository lensesBrandRepository;

    public LensesOfferServiceImpl(ModelRepository modelRepository, LensesOfferRepository lensesOfferRepository, ModelMapper modelMapper, LensesBrandRepository lensesBrandRepository) {
        this.modelRepository = modelRepository;
        this.lensesOfferRepository = lensesOfferRepository;
        this.modelMapper = modelMapper;

        this.lensesBrandRepository = lensesBrandRepository;
    }

    @Override
    public void initializeLensesOffers() {
        LensesBrandEntity morani = lensesBrandRepository.findByName("Roberto").orElse(null);
        if(lensesOfferRepository.count()==0){
            LensesOfferEntity lenses1=new LensesOfferEntity();

            lenses1.setCategory(LensesCategoryEnum.ANNUAL);
            lenses1.setModel(modelRepository.findById(1L).orElse(null));
            lenses1.setYearOfCreation(2019);
            lenses1.setBrand(morani);
            lenses1.setImageUrl("/images/image/vitality_1_2.jpg");
            lenses1.setColor("Black");
            lenses1.setDescription("Very good looking");
            lenses1.setPrice(99.99);

            LensesOfferEntity lenses2=new LensesOfferEntity();
            lenses2.setModel(modelRepository.findById(2L).orElse(null));
            lenses2.setCategory(LensesCategoryEnum.MONTHLY);

            lenses2.setYearOfCreation(2019);
            lenses2.setBrand(morani);
            lenses2.setImageUrl("/images/image/clariti_multifocal_2.jpg");
            lenses2.setColor("Black");
            lenses2.setDescription("Very good looking");
            lenses2.setPrice(99.99);

            lensesOfferRepository.saveAll(List.of(lenses1,lenses2));
        }

    }

    @Override
    public List<LensesSummaryView> getAllLenses() {
        return lensesOfferRepository.findAll().stream().map(this::map).collect(Collectors.toList());
    }

    @Override
    public LensesOfferDetailedView findById(Long id) {
        return this.lensesOfferRepository.findById(id).map(this::mapDetailedView).get();
    }

    @Override
    public void deleteOffer(Long id) {
        lensesOfferRepository.deleteById(id);
    }

    @Override
    public void updateOffer(LensesOfferUpdateServiceModel serviceModel) {
        LensesOfferEntity lensesOfferEntity=lensesOfferRepository.findById(serviceModel.getId())
                .orElseThrow(()->new ObjectNotFoundException("Offer with id:"+serviceModel.getId()+"not found!"));

        lensesOfferEntity.setCategory(serviceModel.getCategory());
       lensesOfferEntity.setPrice(serviceModel.getPrice());
       lensesOfferEntity.setYearOfCreation(serviceModel.getYearOfCreation());
        lensesOfferEntity.setImageUrl(serviceModel.getImageUrl());
       lensesOfferEntity.setColor(serviceModel.getColor());
        lensesOfferEntity.setDescription(serviceModel.getDescription());

        lensesOfferRepository.save(lensesOfferEntity);
    }

    @Override
    public LensesOfferAddServiceModel addOffer(LensesOfferAddBindingModel lensesOfferAddBindingModel) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        LensesOfferAddServiceModel lensesOfferAddServiceModel=modelMapper.map(lensesOfferAddBindingModel,LensesOfferAddServiceModel.class);
        Optional<LensesBrandEntity> brandEntity = lensesBrandRepository.findByName(lensesOfferAddBindingModel.getBrand().getName());
        LensesBrandEntity newBrandEntity = null;
        if(brandEntity.isEmpty()){
            newBrandEntity = new LensesBrandEntity();
            newBrandEntity.setName(lensesOfferAddBindingModel.getBrand().getName());
            newBrandEntity = lensesBrandRepository.save(newBrandEntity);
        }
        else {
            newBrandEntity = brandEntity.get();
        }
        LensesOfferEntity newLensesOffer=modelMapper.map(lensesOfferAddServiceModel,LensesOfferEntity.class);
        ModelEntity model=modelRepository.getById(lensesOfferAddBindingModel.getModel().getId());
        newLensesOffer.setModel(model);
        newLensesOffer.setBrand(newBrandEntity);
        newLensesOffer.setCategory(lensesOfferAddServiceModel.getCategory());
        LensesOfferEntity savedOffer=lensesOfferRepository.save(newLensesOffer);
        return modelMapper.map(savedOffer,LensesOfferAddServiceModel.class);
    }


    private LensesSummaryView map(LensesOfferEntity lensesOfferEntity){
       LensesSummaryView lensesSummaryView =this.modelMapper.map(lensesOfferEntity, LensesSummaryView.class);

       lensesSummaryView.setBrand(lensesOfferEntity.getBrand().getName());
        lensesSummaryView.setPrice(lensesOfferEntity.getPrice().intValue());
       lensesSummaryView.setModel(lensesOfferEntity.getModel().getName());
       lensesSummaryView.setImageUrl(lensesOfferEntity.getImageUrl());

        return lensesSummaryView;
    }
    private LensesOfferDetailedView mapDetailedView(LensesOfferEntity lensesOfferEntity) {
        LensesOfferDetailedView lensesOfferDetailedView = this.modelMapper.map(lensesOfferEntity, LensesOfferDetailedView.class);
        lensesOfferDetailedView.setModel(lensesOfferEntity.getModel().getName());
        lensesOfferDetailedView.setCategory(lensesOfferEntity.getCategory());
        lensesOfferDetailedView.setBrand(lensesOfferEntity.getBrand());
        lensesOfferDetailedView.setColor(lensesOfferEntity.getColor());
        lensesOfferDetailedView.setDescription(lensesOfferEntity.getDescription());
        lensesOfferDetailedView.setImageUrl(lensesOfferEntity.getImageUrl());
        lensesOfferDetailedView.setPrice(lensesOfferEntity.getPrice());
        lensesOfferDetailedView.setYearOfCreation(lensesOfferEntity.getYearOfCreation());
        return lensesOfferDetailedView;
    }
}
