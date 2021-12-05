package com.example.optikafiness.service;

import com.example.optikafiness.model.entity.binding.LensesOfferAddBindingModel;
import com.example.optikafiness.model.entity.service.LensesOfferAddServiceModel;
import com.example.optikafiness.model.entity.service.LensesOfferUpdateServiceModel;
import com.example.optikafiness.model.entity.view.LensesOfferDetailedView;
import com.example.optikafiness.model.entity.view.LensesSummaryView;

import java.util.List;

public interface LensesOfferService {
    void initializeLensesOffers();

    List<LensesSummaryView> getAllLenses();

    LensesOfferDetailedView findById(Long id);


    void deleteOffer(Long id);

    void updateOffer(LensesOfferUpdateServiceModel serviceModel);

    LensesOfferAddServiceModel addOffer(LensesOfferAddBindingModel lensesOfferAddBindingModel);
}
