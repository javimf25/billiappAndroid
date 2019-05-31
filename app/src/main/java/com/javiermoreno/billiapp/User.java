package com.javiermoreno.billiapp;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String Id;
    private String Password;
    private String email;
    private String Username;
    private boolean esAdmin;
    private Integer idclub;
    private int punts;

    public User(String Id,String Usernamein, String email, String passwordin) {
        this.Id=Id;
        this.Username = Usernamein;
        this.email=email;
        this.Password = passwordin;
    }

    public Integer getIdclub() {
        return idclub;
    }

    public void setIdclub(Integer idclub) {
        this.idclub = idclub;
    }

    public User(String id, String username, int punts, int idclub) {
        Id = id;
        Username = username;
        this.punts = punts;
        this.idclub=idclub;
    }

    protected User(Parcel in) {
        Id = in.readString();
        Password = in.readString();
        email = in.readString();
        Username = in.readString();
        esAdmin = in.readByte() != 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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


    public int getPunts() {
        return punts;
    }

    public void setPunts(int punts) {
        this.punts = punts;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Id);
        dest.writeString(Password);
        dest.writeString(email);
        dest.writeString(Username);
        dest.writeByte((byte) (esAdmin ? 1 : 0));
    }
}
