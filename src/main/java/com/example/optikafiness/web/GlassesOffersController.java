package com.example.optikafiness.web;

import com.example.optikafiness.model.entity.binding.GlassesOfferAddBindingModel;
import com.example.optikafiness.model.entity.binding.GlassesOfferUpdateBindingModel;
import com.example.optikafiness.model.entity.enums.CategoryEnum;
import com.example.optikafiness.model.entity.service.GlassesOfferAddServiceModel;
import com.example.optikafiness.model.entity.service.GlassesOfferUpdateServiceModel;
import com.example.optikafiness.model.entity.view.GlassesOfferDetailedView;
import com.example.optikafiness.service.BrandService;
import com.example.optikafiness.service.GlassesOfferService;
import com.example.optikafiness.service.StatsService;
import com.example.optikafiness.service.UserService;
import com.example.optikafiness.service.impl.OptikaFinessUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;


@Controller
public class GlassesOffersController {
    private final GlassesOfferService glassesOfferService;
    private final ModelMapper modelMapper;
    private final BrandService brandService;
    private final StatsService statsService;
    private final UserService userService;

    public GlassesOffersController(GlassesOfferService glassesOfferService, ModelMapper modelMapper, BrandService brandService, StatsService statsService, UserService userService) {
        this.glassesOfferService = glassesOfferService;
        this.modelMapper = modelMapper;
        this.brandService = brandService;
        this.statsService = statsService;
        this.userService = userService;
    }


    @GetMapping("/offers/glasses")
    public String glassesOffers(Model model){
        model.addAttribute("glasses",glassesOfferService.getAllGlasses());
        return "Glasses";
    }


    @GetMapping("/offers/{id}/details")
    public String showGlassesOffer(@PathVariable Long id, Model model){
        model.addAttribute("offer",this.glassesOfferService.findById(id));
        model.addAttribute("stats", statsService.getStats());
        return "GlassesOffer";

    }
    @PreAuthorize("@userServiceImpl.isOwner(#principal.name)")
    @DeleteMapping("/offers/{id}")
    public String deleteGlassesOffer(@PathVariable Long id,Principal principal){


        glassesOfferService.deleteOffer(id);
        return "redirect:/offers/glasses";
    }
    @PreAuthorize("@userServiceImpl.isOwner(#principal.name)")
    @GetMapping("/offers/{id}/edit")
    public String editGlassesOffer(@PathVariable Long id,Model model,Principal principal){
        GlassesOfferDetailedView glassesOfferDetailedView=glassesOfferService.findById(id);
        GlassesOfferUpdateBindingModel glassesOfferUpdateBindingModel=modelMapper.map(glassesOfferDetailedView,GlassesOfferUpdateBindingModel.class);
        model.addAttribute("models", CategoryEnum.values());
        model.addAttribute("glassesModel",glassesOfferUpdateBindingModel);
        return "EditGlasses";
    }
    @PreAuthorize("@userServiceImpl.isOwner(#principal.name)")
    @GetMapping("/offers/{id}/edit/errors")
    public String editOfferErrors(@PathVariable Long id, Model model,Principal principal) {
        model.addAttribute("models", CategoryEnum.values());
        return "EditGlasses";
    }
    @PreAuthorize("@userServiceImpl.isOwner(#principal.name)")
    @PatchMapping("/offers/{id}/edit")
    public String editGlassesOffer(
            @PathVariable Long id,
            @Valid GlassesOfferUpdateBindingModel glassesOfferUpdateBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,Principal principal) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("offerModel", glassesOfferUpdateBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.glassesOfferModel", bindingResult);

            return "redirect:/offers/" + id + "/edit/errors";
        }

        GlassesOfferUpdateServiceModel serviceModel = modelMapper.map(glassesOfferUpdateBindingModel,
                GlassesOfferUpdateServiceModel.class);
        serviceModel.setId(id);

        glassesOfferService.updateOffer(serviceModel);

        return "redirect:/offers/" + id + "/details";
    }
    @GetMapping("/offers/add")
    @PreAuthorize("@userServiceImpl.isOwner(#principal.name)")
    public String getGlassesAddOfferPage(Model model,Principal principal) {

        if (!model.containsAttribute("glassesOfferAddBindModel")) {
            model.
                    addAttribute("glassesOfferAddBindModel", new GlassesOfferAddBindingModel()).
                    addAttribute("brandsModels", brandService.getAllGlassesBrands());
        }
        return "GlassesAdd";
    }
    @PreAuthorize("@userServiceImpl.isOwner(#principal.name)")
    @PostMapping("/offers/add")
    public String addGlassesOffer(@Valid GlassesOfferAddBindingModel glassesOfferAddBindModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           Principal principal) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("glassesOfferAddBindModel", glassesOfferAddBindModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.glassesOfferAddBindModel", bindingResult)
                    .addFlashAttribute("brandsModels", brandService.getAllGlassesBrands());
            return "redirect:/offers/add";
        }
        GlassesOfferAddServiceModel savedOfferAddServiceModel = glassesOfferService.addOffer(glassesOfferAddBindModel);
        return "redirect:/offers/" + savedOfferAddServiceModel.getId() + "/details";
    }

}
