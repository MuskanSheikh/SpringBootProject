package com.Spring.SpringBoot.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Table(name="user")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //user name should not be null or empty
    //user name must be unique
    @Column(name="username", nullable = false, unique = true)
    @NotEmpty
    private String username;

    @NotBlank
    @Column(name="firstname", nullable = false)
    private String firstname;

    @NotBlank
    @Column(name="lastname", nullable = false)
    private String lastname;

    //email should be a valid email format
    //email should not null or empty
    @NotBlank
    @Email
    private String email;

    //password should not be null or empty
    //password should have atleast 8 character
    @NotEmpty
    //@Size(min=8, message = "Minimum eight characters")
    /*@Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z])(?=.*[\\W_])",
            message = "at least one uppercase letter, " +'\'' +
            "one lowercase letter," +'\'' +
            " one number and one special character:")*/
    private String password;



    public User() {
    }

    public User(String username, String firstname, String lastname, String email, String password) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
