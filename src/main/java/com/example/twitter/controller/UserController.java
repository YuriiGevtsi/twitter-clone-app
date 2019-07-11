package com.example.twitter.controller;

import com.example.twitter.domain.Role;
import com.example.twitter.domain.User;
import com.example.twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("all")@PreAuthorize("hasAnyAuthority('ADMIN')")
    public String userList(Model model){
        model.addAttribute("users",userService.findAll());
        return "userList";
    }

    @GetMapping("{user}") @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String userEditForm(@PathVariable User user, Model model){
        model.addAttribute("user",user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }


    @PostMapping @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String userSave(@RequestParam("userId")User user, @RequestParam Map<String,String> form,@RequestParam String username){
        userService.saveUser(user,username,form);
        return "redirect:/user/all";
    }

    @GetMapping("proFile")
    public String getProFile (Model model, @AuthenticationPrincipal User user){
        model.addAttribute("username",user.getUsername());
        model.addAttribute("email",user.getEmail());
        return "proFile";
    }

    @PostMapping("proFile")
    public String saveProfile (@AuthenticationPrincipal User user,@RequestParam String password, @RequestParam String email){
        userService.updateProfile(user,password,email);
        return "redirect:/user/proFile";
    }

    @GetMapping("subscribe/{user}")
    public String subscribe(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user
    ){
        userService.subscribe(currentUser,user);
        return "redirect:/user-messages/"+ user.getId();
    }

    @GetMapping("unsubscribe/{user}")
    public String unsubscribe(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user
    ){
        userService.unsubscribe(currentUser,user);
        return "redirect:/user-messages/"+ user.getId();
    }

    @GetMapping("{type}/{user}/list")
    public String userList(Model model,
                           @PathVariable User user,
                           @PathVariable String type)
    {
        model.addAttribute("userChannel",user);
        model.addAttribute("type",type);

        if ("subscriptions".equals(type))model.addAttribute("users",user.getSubscriptions());
        else model.addAttribute("users",user.getSubscribers());
        return "subscriptions";
    }

}
