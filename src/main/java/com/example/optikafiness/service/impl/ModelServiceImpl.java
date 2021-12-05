package com.example.optikafiness.service.impl;

import com.example.optikafiness.model.entity.BrandEntity;
import com.example.optikafiness.model.entity.ModelEntity;
import com.example.optikafiness.repository.BrandRepository;
import com.example.optikafiness.repository.ModelRepository;
import com.example.optikafiness.service.ModelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;

    public ModelServiceImpl(ModelRepository modelRepository) {

        this.modelRepository = modelRepository;
    }

    @Override
    public void initializeModel() {
        if(modelRepository.count()==0) {

            ModelEntity adriana = new ModelEntity();

            adriana.setName("Adriana");

            ModelEntity guliani=new ModelEntity();
            guliani.setName("Guliani");

            modelRepository.saveAll(List.of(adriana,guliani));
        }
    }
}
