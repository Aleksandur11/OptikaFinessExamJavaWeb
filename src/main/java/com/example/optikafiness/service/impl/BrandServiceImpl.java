package com.example.optikafiness.service.impl;

import com.example.optikafiness.model.entity.BrandEntity;
import com.example.optikafiness.model.entity.GlassesOfferEntity;
import com.example.optikafiness.model.entity.LensesOfferEntity;
import com.example.optikafiness.model.entity.view.GlassesBrandViewModel;
import com.example.optikafiness.model.entity.view.LensesBrandViewModel;
import com.example.optikafiness.model.entity.view.ModelViewModel;
import com.example.optikafiness.repository.BrandRepository;
import com.example.optikafiness.repository.LensesBrandRepository;
import com.example.optikafiness.service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {
  private final BrandRepository brandRepository;
  private final ModelMapper modelMapper;


    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;


    }

    @Override
    public void initializeBrand() {
        if(brandRepository.count()==0){
            BrandEntity morani=new BrandEntity();
            morani.setName("Morani");
            brandRepository.save(morani);
            BrandEntity giovanni=new BrandEntity();
            giovanni.setName("Giovanni");
            brandRepository.save(giovanni);

        }
    }

    @Override
    public List<GlassesBrandViewModel> getAllGlassesBrands() {

        return brandRepository.findAll()
                .stream()
                .map(brandEntity -> {
                    GlassesBrandViewModel glassesBrandViewModel=new GlassesBrandViewModel();
                            glassesBrandViewModel.setName(brandEntity.getName());

                            glassesBrandViewModel.setModels(brandEntity.getGlasses()
                                    .stream().map(this::map).collect(Collectors.toList()));
                            return glassesBrandViewModel;
                }).collect(Collectors.toList());
    }



    private ModelViewModel map(GlassesOfferEntity glassesOfferEntity) {
        return modelMapper.map(glassesOfferEntity, ModelViewModel.class);
    }

}
