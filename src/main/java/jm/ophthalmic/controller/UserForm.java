package jm.ophthalmic.controller;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserForm {
    private String int_id;
    private String int_pw1;
    private String int_pw2;
    private String int_name;
    private String int_email;
    private String int_yy;
    private String int_mm;
    private String int_dd;
    private String int_gender;
    private String int_contact;
    public void setInt_id(String int_id) {
        this.int_id = int_id;
    }
    public void setInt_pw1(String int_pw1) {
        this.int_pw1 = int_pw1;
    }
    public void setInt_pw2(String int_pw2) {
        this.int_pw2 = int_pw2;
    }
    public void setInt_name(String int_name) {
        this.int_name = int_name;
    }
    public void setInt_email(String int_email) {
        this.int_email = int_email;
    }
    public void setInt_yy(String int_yy) {
        this.int_yy = int_yy;
    }
    public void setInt_mm(String int_mm) {
        this.int_mm = int_mm;
    }
    public void setInt_dd(String int_dd) {
        this.int_dd = int_dd;
    }
    public void setInt_gender(String int_gender) {
        this.int_gender = int_gender;
    }
    public void setInt_contact(String int_contact) {
        this.int_contact = int_contact;
    }
    public String getAccount() {
        return int_id;
    }
    public String getPassword() {
        return int_pw1;
    }
    
    public String getName() {
        return int_name;
    }
    
    public String getContact() {
        return int_contact;
    }
    
    public String getEmail() {
        return int_email;
    }
    
    public String getGender() {
        return int_gender;
    }
    
    public LocalDate getBirth() {
        return LocalDate.of(Integer.parseInt(int_yy),Integer.parseInt(int_mm),Integer.parseInt(int_dd));
    }
    public String getInt_pw2() {
        return int_pw2;
    }
    
}
