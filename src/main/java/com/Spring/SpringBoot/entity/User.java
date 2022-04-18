package com.Spring.SpringBoot.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Table(name="user")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="name", nullable = false)
    //user name should not be null or empty
    //user name should have at least 5 character
    @NotEmpty
    @Size(min=5, message = "user name must have atleast 5 character")
    private String name;
    //email should be a valid email format
    //email should not null or empty
    @NotEmpty
    @Email
    private String email;
    //password should not be null or empty
    //password should have atleast 8 character
    @NotEmpty
    //@Size(min=8, message = "Minimum eight characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Minimum eight characters, " +'\'' +
            "at least one uppercase letter, " +'\'' +
            "one lowercase letter," +'\'' +
            " one number and one special character:")
    private String password;

    public User() {
    }

    public User(long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
