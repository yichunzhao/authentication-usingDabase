package com.ynz.demobasicauthentication;

import com.ynz.demobasicauthentication.entities.Role;
import com.ynz.demobasicauthentication.entities.User;
import com.ynz.demobasicauthentication.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

@SpringBootApplication
public class DemoBasicAuthenticationApplication implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoBasicAuthenticationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Role role1 = new Role("USER");
        Role role2 = new Role("ADMIN");

        Set<Role> roles = Stream.of(role1, role2).collect(toSet());
        String loginName = "test";
        String password = passwordEncoder().encode("test");

        User user = User.builder().loginName(loginName).password(password)
                .roles(Stream.of(role1, role2).collect(toSet())).build();

        String loginName1 = "nob";
        String password1 = passwordEncoder().encode("nob");
        User user1 = User.builder().loginName(loginName1).password(password1)
                .roles(Stream.of(role1).collect(toSet())).build();

        userRepository.save(user);

        userRepository.save(user1);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
