package com.example.optikafiness.web;

import com.example.optikafiness.model.entity.binding.UserLoginBindingModel;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserLoginController {


    @GetMapping("/users/login")
    public String login(){
        return "Login";
    }

    @PostMapping("/users/login-error")
    public String failedLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                                          String username, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("bad_credentials",true);
        redirectAttributes.addFlashAttribute("username",username);

        return "redirect:/users/login";

    }

}
