package com.ds.kukushkin.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.UUID;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "user_table")
public class UserEntity {

    @Id
    @GeneratedValue
    UUID id;

    @Column(name = "name")
    String name;

    @Column(name = "lastname")
    String lastname;

    @Column(name = "patronymic")
    String patronymic;

    @Column(name = "birthday")
    LocalDate birthday;

    @Column(name = "password")
    String password;

    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    String email;

    @Pattern(regexp="(^$|[0-9]{10})")
    String phoneNumber;

    @JsonBackReference
    @ManyToOne
    @JoinTable(
            name = "role_user",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    Role role;

    public UserEntity(String name,
                      String lastname,
                      String patronymic,
                      LocalDate birthday,
                      String password,
                      @Email(message = "Email is not valid",
                              regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$") String email,
                      @Pattern(regexp = "(^$|[0-9]{10})") String phoneNumber) {
        this.name = name;
        this.lastname = lastname;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
