package com.ynz.demobasicauthentication.entities;

import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class UserTest {

    @Test
    public void testBuilderFromConstructor() {

        Role role1 = new Role("USER");
        Role role2 = new Role("ADMIN");

        Set<Role> roles = Stream.of(role1, role2).collect(toSet());
        String loginName = "xx@gmail.com";
        String password = "1234";

        User user = User.builder().loginName(loginName).password(password)
                .roles(Stream.of(role1, role2).collect(toSet())).build();

        assertThat(user.getLoginName(), is(loginName));
        assertThat(user.getPassword(), is(password));
        assertThat(user.getRoles(), is(equalTo(roles)));
    }

}