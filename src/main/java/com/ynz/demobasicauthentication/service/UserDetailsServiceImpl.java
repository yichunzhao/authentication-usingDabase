package com.ynz.demobasicauthentication.service;

import com.ynz.demobasicauthentication.entities.User;
import com.ynz.demobasicauthentication.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLoginName(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " is not found."));

        return mapToUserDetails(user);
    }

    private UserDetails mapToUserDetails(User user) {

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getLoginName())
                .password(user.getPassword())
                .roles(user.getRoles().stream().map(role -> role.getRoleName()).toArray(String[]::new))
                .accountExpired(false)
                .credentialsExpired(false)
                .accountLocked(false)
                .disabled(false)
                .build();
    }
}
