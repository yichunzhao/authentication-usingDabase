package com.ynz.demobasicauthentication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class SuperManController {

    @GetMapping("superman")
    public String getSuperMan(){

        return "superman is flying";
    }

    @GetMapping("logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "logout";
    }
}
