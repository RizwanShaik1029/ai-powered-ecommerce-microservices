package com.srb.order.model;

public class UserDto {

    private Long id;

    private String username;

    private String phNumber;

    private String email;

    private int age;

    public UserDto(Long id, String username, String phNumber, String email, int age) {
        this.id = id;
        this.username = username;
        this.phNumber = phNumber;
        this.email = email;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPhNumber() {
        return phNumber;
    }

    public void setPhNumber(String phNumber) {
        this.phNumber = phNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
