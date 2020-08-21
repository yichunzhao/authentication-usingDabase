package com.ynz.demobasicauthentication.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String loginName;
    private String password;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<Role> roles;

    @Builder
    public User(String loginName, String password, Set<Role> roles) {
        this.loginName = loginName;
        this.password = password;

        setRoleUser(roles);
    }

    private void setRoleUser(Set<Role> roles) {
        this.roles = roles;
        roles.forEach(role -> role.addUser(this));
    }
}
