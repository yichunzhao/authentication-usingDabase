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
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

        User user = found.get();

        assertThat(user.getPassword(), is("1234"));
        assertThat(user.getRoles(), hasSize(2));
    }

    @Test
    void findByLoginNameNotExisted() {
        Optional<User> found = repository.findByLoginName("yy@gmail.com");
        assertFalse(found.isPresent());
    }

    @Test
    void persistUser() {
        Role role1 = new Role("USER");
        Role role2 = new Role("ADMIN");

        Set<Role> roles = Stream.of(role1, role2).collect(toSet());
        String loginName = "xx@gmail.com";
        String password = "1234";

        User user = User.builder().loginName(loginName).password(password)
                .roles(Stream.of(role1, role2).collect(toSet())).build();

        User persisted = repository.save(user);
        assertThat(persisted, is(notNullValue()));

        User found = testEntityManager.find(User.class, persisted.getId());
        assertThat(found, is(notNullValue()));

        assertThat(found.getRoles(), is(notNullValue()));
        assertThat(found.getRoles(), hasSize(2));
    }

}