package com.ynz.demobasicauthentication.front;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class LoginController {

    @GetMapping("/login")
    public String home() {
        return "login";
    }

    @PostMapping("/loginSuccess")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public String loginSuccess(HttpServletRequest request) {
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
