package com.iprwc.iprwc_backend_project.model;

import jakarta.persistence.*;


@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "username")
    private String name;
    @Column(name = "encryptedpassword")
    private String password;
    @Column(name = "salt")
    private String salt;
    @Enumerated(jakarta.persistence.EnumType.STRING)
    @Column(name = "role")
    private RoleType role;

    public Account(String name, String password, String salt, RoleType role) {
        this.name = name;
        this.password = password;
        this.salt = salt;
        this.role = role;
    }

    public Account() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String username) {
        this.name = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
