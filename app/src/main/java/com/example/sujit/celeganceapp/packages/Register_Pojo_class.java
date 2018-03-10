package com.example.sujit.celeganceapp.packages;

/**
 * Created by Jitu Nayak on 3/8/2018.
 */

public class Register_Pojo_class {
    String name;
    String branch;
    String reg_id;
    long phone_number;
    String eventName;

    public Register_Pojo_class(String name, String branch, String reg_id, long phone_number,String eventName) {
        this.name = name;
        this.branch = branch;
        this.reg_id = reg_id;
        this.phone_number = phone_number;
        this.eventName = eventName;
    }



    public String getName() {
        return name;

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getReg_id() {
        return reg_id;
    }

    public void setReg_id(String reg_id) {
        this.reg_id = reg_id;
    }

    public long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(long phone_number) {
        this.phone_number = phone_number;
    }
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

}
