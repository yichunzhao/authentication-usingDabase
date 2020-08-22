package com.ynz.demobasicauthentication.front;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@Slf4j
public class LogoutController {

    @GetMapping("/logout-success")
    public String logout(Principal principal) {
        if (principal instanceof UserDetails)
            log.info(((UserDetails) principal).getUsername());

        return "logoutSuccess";
    }
}
