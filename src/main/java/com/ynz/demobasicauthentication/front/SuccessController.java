package com.ynz.demobasicauthentication.front;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
@Slf4j
public class SuccessController {

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAdminPage(Principal principal, Model model) {

        if (principal instanceof UsernamePasswordAuthenticationToken) {
            log.info("username :" + principal.getName());
            model.addAttribute("currentUser", principal.getName());
        }

        return "adminpage";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public String getUserPage(Principal principal, Model model) {

        if (principal instanceof UsernamePasswordAuthenticationToken) {
            log.info("username :" + principal.getName());
            model.addAttribute("currentUser", principal.getName());
        }

        return "userpage";
    }

    @GetMapping("/principal")
    @ResponseBody
    public String hello(Principal principal) {
        return principal.toString();
    }


}
