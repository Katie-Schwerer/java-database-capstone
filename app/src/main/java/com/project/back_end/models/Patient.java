package com.project.back_end.models;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 100)
    private String name;

    @Email
    @NotNull
    private String email;

    @Size(min = 6)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Pattern(regexp = "\\d{10}")
    private String phone;

    @NotNull
    @Size(max = 255)
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long newId) {
        this.id = newId;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassWord(String newPassword) {
        this.password = newPassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String newPhone) {
        this.phone = newPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String newAddress) {
        this.address = newAddress;
    }
}
