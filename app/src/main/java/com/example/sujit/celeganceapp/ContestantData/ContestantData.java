package com.example.sujit.celeganceapp.ContestantData;

/**
 * Created by sujit on 2/28/2018.
 */

public class ContestantData  {


    private String name;
    private String reg;


    private String branch;
    private String phone;
    private String status;

    public ContestantData(String name, String reg, String branch,String phone, String status)
    {
        this.name = name;
        this.branch = branch;
        this.reg = reg;
        this.phone =phone;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }

}
