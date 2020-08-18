package com.ynz.demobasicauthentication.repo;

import com.ynz.demobasicauthentication.entities.Role;
import com.ynz.demobasicauthentication.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository repository;

    @BeforeEach
    private void setupDataInDB() {
        Role role1 = new Role("USER");
        Role role2 = new Role("ADMIN");

        Set<Role> roles = Stream.of(role1, role2).collect(toSet());
        String loginName = "xx@gmail.com";
        String password = "1234";

        User user = User.builder().loginName(loginName).password(password)
                .roles(Stream.of(role1, role2).collect(toSet())).build();

        testEntityManager.persist(user);
        testEntityManager.flush();
    }

    @Test
    void findByLoginName() {
        Optional<User> found = repository.findByLoginName("xx@gmail.com");
        assertTrue(found.isPresent());

        assertThat(found.get().getPassword(), is("1234"));
    }
}