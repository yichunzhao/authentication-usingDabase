package com.ynz.demobasicauthentication.front;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class SuccessController {

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAdminPage() {
        return "adminpage";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String getUserPage() {
        return "userpage";
    }

}
