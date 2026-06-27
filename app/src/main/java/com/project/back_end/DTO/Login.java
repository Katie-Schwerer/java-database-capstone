package com.project.back_end.DTO;

public class Login {
    private String email;
    private String password;

    public Login() {}
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

// 4. Getters and Setters:
//    - Standard getter and setter methods are provided for both 'email' and 'password' fields.
//    - The 'getEmail()' method allows access to the email value.
//    - The 'setEmail(String email)' method sets the email value.
//    - The 'getPassword()' method allows access to the password value.
//    - The 'setPassword(String password)' method sets the password value.


}
