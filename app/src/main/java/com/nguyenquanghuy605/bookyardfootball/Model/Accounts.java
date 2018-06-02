package com.nguyenquanghuy605.bookyardfootball.Model;

public class Accounts {
    String username;
    String password;
    String phone;
    String email;
    long role;
    long id;
    String name;

    public Accounts(){}
    public Accounts( long id , String name , String password ,String phone , long role,String username)
    {
        this.id = id;
        this.name = name;
         this.password = password;
         this.phone = phone;
         this.role = role;
         this.username = username;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public long getRole() {
        return role;
    }

    public void setRole(long role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
