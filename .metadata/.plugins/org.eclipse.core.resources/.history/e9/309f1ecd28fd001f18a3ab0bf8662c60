package com.doku.model.Users;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "kid", uniqueConstraints = {@UniqueConstraint(columnNames = "Email")})
public class Kid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(name = "FirstName", nullable = false, length = 30)
    private String first_name;

    @Column(name = "LastName", nullable = false, length = 30)
    private String last_name;

    @Column(name = "Email", nullable = false, unique = true, length = 50)
    private String e_mail;

    @Column(name = "PhoneNumber", nullable = false, unique = true, length = 30)
    private String phone_number;

    @Column(name = "Password", nullable = false, length = 255)
    private String password;

    @Column(name = "DateCreated", nullable = false, updatable = false)
    private LocalDateTime date_created;

    @PrePersist
    protected void onCreate() {
        this.date_created = LocalDateTime.now();
    }

    // Parent ile ilişki kuruluyor
    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = false)
    private Parent parent;

    // ------------------------------------------------
    public Kid() {}

    public Kid(String first_name, String last_name, String e_mail, String phone_number, String password, Parent parent) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.e_mail = e_mail;
        this.phone_number = phone_number;
        this.setPassword(password);
        this.parent = parent; // Parent'ı ekliyoruz
    }

    // Getter ve Setter metodları
    public Long getUserId() {
        return user_id;
    }

    public void setUserId(Long user_id) {
        this.user_id = user_id;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return e_mail;
    }

    public void setEmail(String e_mail) {
        this.e_mail = e_mail;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = PasswordUtils.hashPassword(password);
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }
}
