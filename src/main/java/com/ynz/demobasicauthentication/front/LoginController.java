package com.ynz.demobasicauthentication.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String home() {
        return "login";
    }

    @RequestMapping("/loginSuccess")
    public String loginSuccess() {
        return "loginSuccess";
    }

    @PostMapping("/loginSubmit")
    public void submitLogin() {

    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginErr", true);
        return "login";
    }
}
