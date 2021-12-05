package com.example.optikafiness.init;

import com.example.optikafiness.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {

    private BrandService brandService;
    private ModelService modelService;
    private UserService userService;
    private GlassesOfferService glassesOfferService;
    private LensesOfferService lensesOfferService;
    private LensesBrandService lensesBrandService;

    public DBInit(BrandService brandService, ModelService modelService, UserService userService, GlassesOfferService glassesOfferService, LensesOfferService lensesOfferService, LensesBrandService lensesBrandService) {
        this.brandService = brandService;
        this.modelService = modelService;
        this.userService = userService;
        this.glassesOfferService = glassesOfferService;
        this.lensesOfferService = lensesOfferService;
        this.lensesBrandService = lensesBrandService;
    }

    @Override
    public void run(String... args) throws Exception {
        brandService.initializeBrand();
        lensesBrandService.initializeLensesBrand();
        modelService.initializeModel();
        userService.initializeUsersAndRoles();
        glassesOfferService.initializeGlassesOffers();
        lensesOfferService.initializeLensesOffers();



    }
}
