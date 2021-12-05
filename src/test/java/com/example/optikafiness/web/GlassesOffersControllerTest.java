package com.example.optikafiness.web;

import com.example.optikafiness.model.entity.*;
import com.example.optikafiness.model.entity.enums.LensesCategoryEnum;
import com.example.optikafiness.model.entity.enums.UserRole;
import com.example.optikafiness.repository.UserRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class GlassesOffersControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRoleRepository userRoleRepository;

    private UserEntity testUser;
    private UserRoleEntity adminRole;
    @BeforeEach
    void setUp(){
        adminRole=new UserRoleEntity();
        adminRole = userRoleRepository.findByRole(UserRole.ADMIN);
        testUser=new UserEntity();
        testUser.setRoles(Set.of(adminRole));
    }
    @Test
    void testAllGlassesPage() throws Exception {
        mockMvc.perform(
                        get("/offers/glasses")
                ).andExpect(status().isOk())
                .andExpect(view().name("Glasses"));
    }
    @Test
    void testShowExactlyGlassesPage() throws Exception {
        GlassesOfferEntity glassesOfferEntity=new GlassesOfferEntity();
        glassesOfferEntity.setId(2L);
        ModelEntity model=new ModelEntity();
        BrandEntity brand=new BrandEntity();
        brand.setName("Giovanni");
        model.setName("Pedro");
        mockMvc.perform(
                        get("/offers/"+glassesOfferEntity.getId()+"/details")
                ).andExpect(status().isOk())
                .andExpect(view().name("GlassesOffer"));

    }
}
