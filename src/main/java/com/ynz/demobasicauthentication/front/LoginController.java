package com.ynz.demobasicauthentication.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String home() {
        return "login";
    }

    @PostMapping("/loginSuccess")
    public String loginSuccess() {
        return "loginSuccess";
    }

    @PostMapping("/login")
    public void submitLogin() {

    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginErr", true);
        return "login";
    }
}
