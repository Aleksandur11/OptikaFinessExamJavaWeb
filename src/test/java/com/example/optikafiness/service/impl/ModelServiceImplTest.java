package com.example.optikafiness.service.impl;

import com.example.optikafiness.model.entity.ModelEntity;
import com.example.optikafiness.repository.ModelRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class ModelServiceImplTest {
    private ModelServiceImpl serviceToTest;
    @Mock
    private ModelRepository mockModelRepository;

    private ModelEntity model;
    @BeforeEach
    void init(){
        serviceToTest=new ModelServiceImpl(mockModelRepository);
        model=new ModelEntity();
        model.setName("Morani");


        serviceToTest.initializeModel();
    }

    @Test
    void testInitialize(){
        Mockito.when(mockModelRepository.findAll()).thenReturn(List.of(model));

        var actual=mockModelRepository.findAll().stream().map(ModelEntity::getName).collect(Collectors.joining(","));
        var expected=model.getName();
        Assertions.assertEquals(expected,actual);
    }

}
