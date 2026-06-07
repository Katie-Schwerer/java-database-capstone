package com.project.back_end.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Username cannot be null")
    private String username;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    public Admin() {
        id = 0L;
        username = "";
        password = "";
    }

    public Admin(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getUserName() {
        return username;
    }

    public String getPassWord() {
        return password;
    }

    public void setId(long newId) {
        this.id = newId;
    }

    public void setUserName(String newUserName) {
        this.username = newUserName;
    }

    public void setPassWord(String newPassWord) {
        this.password = newPassWord;
    }

}
