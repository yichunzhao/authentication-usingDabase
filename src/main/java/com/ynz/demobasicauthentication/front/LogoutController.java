package com.ynz.demobasicauthentication.front;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class LogoutController {

    @GetMapping("/logout-success")
    public String logout() {
        return "logoutSuccess";
    }
}
