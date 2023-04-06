package jm.ophthalmic.controller.form;

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
