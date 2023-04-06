package jm.ophthalmic.controller.form;

import lombok.Data;

@Data
public class LoginForm {
    private String user_id;
    private String user_password;

    public String getAccount(){
        return user_id;
    }
    public String getPassword(){
        return user_password;
    }
}
