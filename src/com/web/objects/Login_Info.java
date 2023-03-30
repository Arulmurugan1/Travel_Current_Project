package com.web.objects;

import java.time.LocalDateTime;

public class Login_Info {
    public String username;
    public String password1;
    public String password2;
    public String role;
    public LocalDateTime create_time;
    public String user_id;
    public String altered_user;
    public LocalDateTime last_login;
    public String dob;
    public String gender;
    public String status;
    
    

    public String getPassword1() {
        return this.password1;
    }


    public void setPassword1(String password1) {
        this.password1 = password1;
    }


    public String getPassword2() {
        return this.password2;
    }


    public void setPassword2(String password2) {
        this.password2 = password2;
    }


    public String getDob() {
        return this.dob;
    }


    public String getPassword() {
        return this.password1;
    }


    public void setPassword(String password1) {
        this.password1 = password1;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public String getStatus() {
        return this.status;
    }


    public void setDob(String dob) {
        this.dob = dob;
    }


    public String getGender() {
        return this.gender;
    }


    public void setGender(String gender) {
        this.gender = gender;
    }




    public String getAltered_user() {
        return altered_user;
    }


    public void setAltered_user(String created_user) {
        this.altered_user = created_user;
    }


    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public LocalDateTime getCreate_time() {
        return create_time;
    }
    public void setCreate_time(LocalDateTime create_time) {
        this.create_time = create_time;
    }
    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public LocalDateTime getLast_login() {
        return last_login;
    }


    public void setLast_login(LocalDateTime last_login) {
        this.last_login = last_login;
    }


    @Override
    public String toString() {
        return "Login_Info [username=" + this.username + ", password1=" + this.password1 + ", password2="
                + this.password2 + ", role=" + this.role + ", create_time=" + this.create_time + ", user_id="
                + this.user_id + ", altered_user=" + this.altered_user + ", last_login=" + this.last_login + ", dob="
                + this.dob + ", gender=" + this.gender + ", status=" + this.status + "]";
    }
    
    
}
