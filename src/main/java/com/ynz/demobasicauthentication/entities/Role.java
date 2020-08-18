package com.ynz.demobasicauthentication.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue
    private Long id;
    private String roleName;

    @ManyToOne
    @JoinColumn(name = "USER_FK")
    private User user;

    public Role(String roleName) {
        this.roleName = roleName;
    }
}
