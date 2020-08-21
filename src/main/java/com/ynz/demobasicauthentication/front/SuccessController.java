package com.ynz.demobasicauthentication.front;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
@Slf4j
public class SuccessController {

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAdminPage() {
        return "adminpage";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public String getUserPage(Authentication authentication) {
        if (authentication != null) authentication.name();
        return "userpage";
    }

    @GetMapping("/principal")
    @ResponseBody
    public String hello(Principal principal) {
        return principal.toString();
    }


}
