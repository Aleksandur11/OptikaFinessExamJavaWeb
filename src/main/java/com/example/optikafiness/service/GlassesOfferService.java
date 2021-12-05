package com.example.optikafiness.service;

import com.example.optikafiness.model.entity.binding.GlassesOfferAddBindingModel;
import com.example.optikafiness.model.entity.service.GlassesOfferAddServiceModel;
import com.example.optikafiness.model.entity.service.GlassesOfferUpdateServiceModel;
import com.example.optikafiness.model.entity.view.GlassesOfferDetailedView;
import com.example.optikafiness.model.entity.view.GlassesSummaryView;

import java.util.List;

public interface GlassesOfferService {
    void initializeGlassesOffers();

    List<GlassesSummaryView> getAllGlasses();

    GlassesOfferDetailedView findById(Long id);

    void deleteOffer(Long id);

    void updateOffer(GlassesOfferUpdateServiceModel serviceModel);

    GlassesOfferAddServiceModel addOffer(GlassesOfferAddBindingModel glassesOfferAddBindModel);




}
