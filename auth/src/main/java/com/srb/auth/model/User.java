package com.srb.auth.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private int age;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String password;

    private String email;

    private String phNumber;

    private LocalDateTime creation_time;

    private LocalDateTime lastModified_time;

    @PrePersist
    public void prePersist()
    {
        this.creation_time=LocalDateTime.now();
        this.lastModified_time=LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate()
    {
        this.lastModified_time=LocalDateTime.now();
    }

    public User()
    {

    }

    public User(Long id, String username, int age, Role role, String password,String email, String phNumber, LocalDateTime creation_time, LocalDateTime lastModified_time) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.role = role;
        this.password = password;
        this.email=email;
        this.phNumber=phNumber;
        this.creation_time = creation_time;
        this.lastModified_time = lastModified_time;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhNumber() {
        return phNumber;
    }

    public void setPhNumber(String phNumber) {
        this.phNumber = phNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(LocalDateTime creation_time) {
        this.creation_time = creation_time;
    }

    public LocalDateTime getLastModified_time() {
        return lastModified_time;
    }

    public void setLastModified_time(LocalDateTime lastModified_time) {
        this.lastModified_time = lastModified_time;
    }
}
