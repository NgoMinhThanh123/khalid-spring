package com.nmt.universitysb.controller;

import com.nmt.universitysb.model.User;
import com.nmt.universitysb.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String list(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
                       @RequestParam(name = "kw", required = false) String keyword) {
        int pageSize = 6;
        Page<User> userPage;

        if (keyword != null && !keyword.isEmpty()) {
            userPage = this.userService.findAllByUsernameContaining(keyword, PageRequest.of(page, pageSize));
        } else {
            userPage = this.userService.findAll(PageRequest.of(page, pageSize));
        }

        model.addAttribute("userPage", userPage);
        model.addAttribute("keyword", keyword);

        return "user";
    }

    @GetMapping("/add_user")
    public String addList(Model model) {
        model.addAttribute("add_user", new User());

        return "add_user";
    }

    @GetMapping("/update_user/{id}")
    public String update(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("update_user", this.userService.findById(id));

        return "update_user";
    }

    @PostMapping("/add_user")
    public String add(@ModelAttribute(value = "add_user") @Valid User u,
            BindingResult rs) {
        if(!rs.hasErrors()){
            this.userService.save(u);
            return "redirect:/user";

        }
        return "add_user";
    }
    @PostMapping("/update_user")
    public String update(@ModelAttribute(value = "update_user") @Valid User u,
                      BindingResult rs) {
        if(!rs.hasErrors()){
            this.userService.save(u);
            return "redirect:/user";

        }
        return "update_user";
    }
}
