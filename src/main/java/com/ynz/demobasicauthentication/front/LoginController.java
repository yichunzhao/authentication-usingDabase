package com.ynz.demobasicauthentication.front;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
@Slf4j
public class LoginController {

    @GetMapping("/login")
    public String home() {
        return "login";
    }

    @PostMapping("/loginSuccess")
    public String loginSuccess(Model model) {
        log.info("Http request handler: loginSuccess");

        Object p = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (p != null) {
            String currentUser = ((UserDetails) p).getUsername();
            log.info("current user name : " + currentUser);
            model.addAttribute("currentUser", currentUser);
        }

        return "loginSuccess";
    }

    @PostMapping("/login")
    public void submitLogin() {

    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Principal principal) {
        return principal.getName();
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginErr", true);
        return "login";
    }
}
