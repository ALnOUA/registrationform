package com.example.registrationform.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Indexed;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.*;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "email")
    @Pattern(regexp = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$", message = "{invalid.email}")
    @NotNull
    private String email;


    @Column(name = "firstname")
    @Pattern(regexp = "^[A-Z]+[a-z]{1,15}|[а-щА-ЩЬьЮюЯяЇїІіЄєҐґ]{1,15}", message = "{invalid.firstname}")
    private String firstname;

    @Column(name = "lastname")
    @Pattern(regexp = "^[A-Z]+[a-z]{1,15}|[а-щА-ЩЬьЮюЯяЇїІіЄєҐґ]{1,15}", message = "{invalid.lastname}")

    private String lastname;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private int active;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}