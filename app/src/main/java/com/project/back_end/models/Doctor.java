package com.project.back_end.models;

@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 100)
    private String name;

    @NotNull
    @Size(min = 3, max = 50)
    private String specialty;

    @Email
    @NotNull
    private String email;

    @Size(min = 6)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Pattern(regexp = "\\d{10}")
    private String phone;

    @ElementCollection
    private List<String> availableTimes;

// 8. Getters and Setters:
//    - Standard getter and setter methods are provided for all fields: id, name, specialty, email, password, phone, and availableTimes.
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

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String newSpecialty) {
        this.specialty = newSpecialty;
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

    public List<String> getAvailableTimes() {
        return availableTimes;
    }

    public void setAvailableTimes(List<String> newAvailableTime) {
        this.availableTimes = newAvailableTime;
    }

}

