package com.elec5620.intelligentfinancial.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Version
    private int version;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;  // "customer" or "bank" or "admin"

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Bank bank;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Admin admin;

    public User(String role, String email, String username, String password) {
        this.role = role;
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
