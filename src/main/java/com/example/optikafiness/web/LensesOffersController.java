package com.example.optikafiness.web;

import com.example.optikafiness.model.entity.binding.LensesOfferAddBindingModel;
import com.example.optikafiness.model.entity.binding.LensesOfferUpdateBindingModel;
import com.example.optikafiness.model.entity.enums.CategoryEnum;
import com.example.optikafiness.model.entity.service.LensesOfferAddServiceModel;
import com.example.optikafiness.model.entity.service.LensesOfferUpdateServiceModel;
import com.example.optikafiness.model.entity.view.LensesOfferDetailedView;
import com.example.optikafiness.service.BrandService;
import com.example.optikafiness.service.LensesBrandService;
import com.example.optikafiness.service.LensesOfferService;
import com.example.optikafiness.service.StatsService;
import com.example.optikafiness.service.impl.OptikaFinessUserImpl;
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
public class LensesOffersController {

    private final LensesOfferService lensesOfferService;
    private final ModelMapper modelMapper;
    private final BrandService brandService;
    private final StatsService statsService;
    private final LensesBrandService lensesBrandService;

    public LensesOffersController(LensesOfferService lensesOfferService, ModelMapper modelMapper, BrandService brandService, StatsService statsService, LensesBrandService lensesBrandService) {
        this.lensesOfferService = lensesOfferService;
        this.modelMapper = modelMapper;
        this.brandService = brandService;
        this.statsService = statsService;
        this.lensesBrandService = lensesBrandService;
    }

    @GetMapping("/offers/lenses")
    public String lensesOffers(Model model){
        model.addAttribute("lenses",lensesOfferService.getAllLenses());
        return "Lenses";
    }

    @GetMapping("/offers/lenses/{id}/details")
    public String showLensesOffer(@PathVariable Long id, Model model){
        model.addAttribute("offer",this.lensesOfferService.findById(id));
        model.addAttribute("stats", statsService.getStats());
        return "LensesOffer";

    }
    @PreAuthorize("@userServiceImpl.isOwner(#principal.name)")
    @DeleteMapping("/offers/lenses/{id}")
    public String deleteLensesOffer(@PathVariable Long id, Principal principal){
       lensesOfferService.deleteOffer(id);
        return "redirect:/offers/lenses";
    }
    @GetMapping("/offers/lenses/{id}/edit")
    @PreAuthorize("@userServiceImpl.isOwner(#principal.name)")
    public String editLensesOffer(@PathVariable Long id, Model model,Principal principal){
        LensesOfferDetailedView lensesOfferDetailedView=lensesOfferService.findById(id);
        LensesOfferUpdateBindingModel lensesOfferUpdateBindingModel=modelMapper.map(lensesOfferDetailedView,LensesOfferUpdateBindingModel.class);
        model.addAttribute("models", CategoryEnum.values());
        model.addAttribute("lensesModel",lensesOfferUpdateBindingModel);
        return "EditLenses";
    }
    @GetMapping("/offers/lenses/{id}/edit/errors")
    @PreAuthorize("@userServiceImpl.isOwner(#principal.name)")
    public String editOfferErrors(@PathVariable Long id, Model model,Principal principal) {
        model.addAttribute("models", CategoryEnum.values());
        return "EditLenses";
    }
    @PreAuthorize("@userServiceImpl.isOwner(#principal.name)")
    @PatchMapping("/offers/lenses/{id}/edit")
    public String editLensesOffer(
            @PathVariable Long id,
            @Valid LensesOfferUpdateBindingModel lensesOfferUpdateBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,Principal principal) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("offerModel", lensesOfferUpdateBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.lensesOfferModel", bindingResult);

            return "redirect:/offers/lenses" + id + "/edit/errors";
        }

        LensesOfferUpdateServiceModel serviceModel = modelMapper.map(lensesOfferUpdateBindingModel,
                LensesOfferUpdateServiceModel.class);
        serviceModel.setId(id);

        lensesOfferService.updateOffer(serviceModel);

        return "redirect:/offers/" + id + "/details";
    }
    @PreAuthorize("@userServiceImpl.isOwner(#principal.name)")
    @GetMapping("/offers/lenses/add")
    public String getLensesAddOfferPage(Model model,Principal principal) {

        if (!model.containsAttribute("lensesOfferAddBindModel")) {
            model.
                    addAttribute("lensesOfferAddBindModel", new LensesOfferAddBindingModel()).
                    addAttribute("brandsModels", lensesBrandService.getAllLensesBrands());
        }
        return "LensesAdd";
    }

    @PostMapping("/offers/lenses/add")
    @PreAuthorize("@userServiceImpl.isOwner(#principal.name)")
    public String addLensesOffer(@Valid LensesOfferAddBindingModel lensesOfferAddBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes,
                                  Principal principal) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("lensesOfferAddBindModel", lensesOfferAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.lensesOfferAddBindModel", bindingResult)
                    .addFlashAttribute("brandsModels", lensesBrandService.getAllLensesBrands());
            return "redirect:/offers/lenses/add";
        }
        LensesOfferAddServiceModel savedOfferAddServiceModel = lensesOfferService.addOffer(lensesOfferAddBindingModel);
        return "redirect:/offers/lenses/" + savedOfferAddServiceModel.getId() + "/details";
    }

}
