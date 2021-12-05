package com.example.optikafiness.web;

import com.example.optikafiness.model.entity.*;
import com.example.optikafiness.model.entity.binding.LensesOfferAddBindingModel;
import com.example.optikafiness.model.entity.enums.LensesCategoryEnum;
import com.example.optikafiness.model.entity.enums.UserRole;
import com.example.optikafiness.repository.LensesOfferRepository;
import com.example.optikafiness.repository.UserRoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
class LensesOffersControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private LensesOfferRepository lensesOfferRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    private UserRoleEntity adminRole;
    private UserEntity testUser;

    @BeforeEach
    void setUp(){
        adminRole=new UserRoleEntity();
        adminRole = userRoleRepository.findByRole(UserRole.ADMIN);
        testUser=new UserEntity();
        testUser.setRoles(Set.of(adminRole));
    }

    @Test
    void testOpenLensesAddPage() throws Exception {
        mockMvc.perform(
                        get("/offers/lenses/add")
                ).andExpect(status().is3xxRedirection());

    }

    @Test
    void testAllLensesPage() throws Exception {
        mockMvc.perform(
                        get("/offers/lenses")
                ).andExpect(status().isOk())
                .andExpect(view().name("Lenses"));
    }
    @Test
    void testShowExactlyLensePage() throws Exception {
        LensesOfferEntity lensesOfferEntity=new LensesOfferEntity();
        lensesOfferEntity.setId(2L);
        ModelEntity model=new ModelEntity();
        LensesBrandEntity brand=new LensesBrandEntity();
        brand.setName("Giovanni");
        model.setName("Pedro");
        lensesOfferEntity.setModel(model);
        lensesOfferEntity.setCategory(LensesCategoryEnum.ANNUAL);
        lensesOfferEntity.setBrand(brand);
        lensesOfferEntity.setImageUrl("/images/image/cool-sunglasses-isolated-white-background-top-view.jpg");
        lensesOfferEntity.setColor("red");
        lensesOfferEntity.setDescription("nicelenses");
        lensesOfferEntity.setPrice(1000.00);
        lensesOfferEntity.setYearOfCreation(1999);
        mockMvc.perform(
                        get("/offers/lenses/"+lensesOfferEntity.getId()+"/details")
                ).andExpect(status().isOk())
                .andExpect(view().name("LensesOffer"));

    }
}