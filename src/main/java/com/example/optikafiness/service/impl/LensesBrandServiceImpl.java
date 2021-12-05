package com.example.optikafiness.service.impl;

import com.example.optikafiness.model.entity.LensesBrandEntity;
import com.example.optikafiness.model.entity.LensesOfferEntity;
import com.example.optikafiness.model.entity.view.LensesBrandViewModel;
import com.example.optikafiness.model.entity.view.ModelViewModel;
import com.example.optikafiness.repository.LensesBrandRepository;
import com.example.optikafiness.service.LensesBrandService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class LensesBrandServiceImpl implements LensesBrandService {
    private final LensesBrandRepository lensesBrandRepository;
    private final ModelMapper modelMapper;

    public LensesBrandServiceImpl(LensesBrandRepository lensesBrandRepository, ModelMapper modelMapper) {
        this.lensesBrandRepository = lensesBrandRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<LensesBrandViewModel> getAllLensesBrands() {
        return lensesBrandRepository.findAll()
                .stream()
                .map(LensesBrandEntity -> {
                    LensesBrandViewModel lensesBrandViewModel=new LensesBrandViewModel();
                    lensesBrandViewModel.setName(LensesBrandEntity.getName());

                    lensesBrandViewModel.setModels(LensesBrandEntity.getLenses()
                            .stream().map(this::mapLenses).collect(Collectors.toList()));
                    return lensesBrandViewModel;
                }).collect(Collectors.toList());
    }

    @Override
    public void initializeLensesBrand() {
        if(lensesBrandRepository.count()==0){
            LensesBrandEntity roberto=new LensesBrandEntity();
            roberto.setName("Roberto");
            lensesBrandRepository.save(roberto);

        }
    }
    private ModelViewModel mapLenses(LensesOfferEntity lensesOfferEntity) {
        return modelMapper.map(lensesOfferEntity, ModelViewModel.class);
    }
}
