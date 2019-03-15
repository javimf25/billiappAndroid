package com.javiermoreno.billiapp;

public class User {
    private String Id;
    private String Password;
    private String email;
    private String Username;
    private boolean esAdmin;

    public User(String Id,String Usernamein, String email, String passwordin) {
        this.Id=Id;
        this.Username = Usernamein;
        this.email=email;
        this.Password = passwordin;
    }



    public String getId(){ return this.Id;}

    public User(String id, String username) {
        Id = id;
        Username = username;
    }

    public String getUsername() {
        return this.Username;
    }

    public String getPassword() {
        return this.Password;
    }

    public String getEmail() {
        return this.email;
    }

    public boolean isEsAdmin() {
        return this.esAdmin;
    }
}
