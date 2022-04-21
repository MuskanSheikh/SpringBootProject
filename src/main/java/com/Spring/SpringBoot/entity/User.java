package com.Spring.SpringBoot.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //user name should not be null or empty
    //user name must be unique
    @Column(name="username", nullable = false)
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
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;

    //password should not be null or empty
    //password should have atleast 8 character
    @NotEmpty
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,128}$",
            message = "at least one uppercase letter, " +'\'' +
                    "one lowercase letter," +'\'' +
                    " one number and one special character:")
    /*@Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z])(?=.*[\\W_])")*/
    private String password;
    private boolean isEnabled;



    public User() {
    }

    public User(String username, String firstname, String lastname, String email, String password,boolean isEnabled) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.isEnabled=isEnabled;
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

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean isEnabled) {
        isEnabled = isEnabled;
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
                ", password='" + isEnabled + '\'' +
                '}';
    }
}
