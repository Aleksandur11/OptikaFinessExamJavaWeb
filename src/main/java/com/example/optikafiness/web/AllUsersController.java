package com.example.optikafiness.web;


import com.example.optikafiness.model.entity.binding.UserUpdateBindingModel;

import com.example.optikafiness.model.entity.enums.UserRole;

import com.example.optikafiness.model.entity.service.UserRoleUpdateServiceModel;
import com.example.optikafiness.model.entity.view.UserView;
import com.example.optikafiness.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class AllUsersController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public AllUsersController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }
    @PreAuthorize("@userServiceImpl.isOwner(#principal.name)")
    @GetMapping("/users/all")
    public String allUsers(Model model,Principal principal){
        model.addAttribute("users",userService.getUsers());
        return "All Users";
    }
    @PreAuthorize("@userServiceImpl.isOwner(#principal.name)")
    @GetMapping("/users/edit/all")
    public String editUsers(Model model,Principal principal){
        model.addAttribute("allUsers",userService.getAllUsersExceptCurrentUser(principal.getName()));
        return "EditAllUsers";
    }
    @PreAuthorize("@userServiceImpl.isOwner(#principal.name)")
    @GetMapping("/users/{id}/edit")
    public String editUsersRoles(@PathVariable Long id,Model model,Principal principal){
        UserView userView=userService.findById(id);
        UserUpdateBindingModel userUpdateBindingModel=modelMapper.map(userView,UserUpdateBindingModel.class);
        model.addAttribute("userRoles", UserRole.values());
        model.addAttribute("userModel",userUpdateBindingModel);
        return "editUserRole";
    }
    @PreAuthorize("@userServiceImpl.isOwner(#principal.name)")
    @PatchMapping("/users/{id}/edit")
    public String editUserRoles(
            @PathVariable Long id,
            @Valid UserUpdateBindingModel userUpdateBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,Principal principal) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("userModel",userUpdateBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRoleModel", bindingResult);

            return "redirect:/users/" + id + "/edit";
        }

        UserRoleUpdateServiceModel serviceModel = modelMapper.map(userUpdateBindingModel,
                UserRoleUpdateServiceModel.class);
        serviceModel.setRole(UserRole.ADMIN);

         userService.updateRole(serviceModel);

        return "redirect:/";
    }


@PreAuthorize("@userServiceImpl.isOwner(#principal.name)")
@DeleteMapping("/users/{id}")
public String deleteGlassesOffer(@PathVariable Long id, Principal principal){


    userService.deleteUser(id);
    return "redirect:/users/edit/all";
}

}


