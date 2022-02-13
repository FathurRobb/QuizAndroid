package com.example.quiz;

public class Users {
    int id;
    String nama,email,telepon,password;

    public Users(){

    }

    public Users(String nama, String email, String telepon, String password) {
        this.nama = nama;
        this.email = email;
        this.telepon = telepon;
        this.password = password;
    }

    public Users(int id, String nama, String email, String telepon, String password) {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.telepon = telepon;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
