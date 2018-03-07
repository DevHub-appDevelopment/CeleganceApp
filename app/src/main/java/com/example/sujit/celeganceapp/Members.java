package com.example.sujit.celeganceapp;

/**
 * Created by user on 3/3/2018.
 */

public class Members {
    public String name;
    public String regId;
    public String phone;
    public String qualify;
    public  String branch;
    public Members(){
        }
    public Members(String name, String phone,String qualify ,String regId,String branch) {
        this.name = name;
        this.phone = phone;
        this.qualify = qualify;
        this.regId = regId;
        this.branch = branch;
    }
}
