package com.javiermoreno.billiapp;

public class URLs {
    private static final String ROOT_URL = "http://192.168.1.37/billiapp/Api.php?apicall=";

    public static final String URL_REGISTER = ROOT_URL + "signup";
    public static final String URL_LOGIN= ROOT_URL + "login";
    public static final String URL_CHANGEPASS= ROOT_URL + "changepass";
    public static final String URL_CHANGEUSER= ROOT_URL + "changeuser";
    public static final String URL_CHECKUSER= ROOT_URL + "checkuser";
    public static final String URL_SAVEMATCH=ROOT_URL+"savematch";
    public static final String URL_GETMATCHESUSER=ROOT_URL+"getmatchesuser";

}
