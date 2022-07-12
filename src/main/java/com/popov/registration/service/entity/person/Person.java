package com.popov.registration.service.entity.person;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.popov.registration.service.entity.person.etc.Logins;
import com.popov.registration.service.entity.person.etc.Role;
import com.popov.registration.service.entity.person.etc.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "persons", uniqueConstraints = @UniqueConstraint(columnNames={"email"}))
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "login_id", referencedColumnName = "person_id")
    private Logins logins;
    public Person(String email, String password, String firstName, String lastName, Role role, Status status) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.status = status;
    }

    public Person(String email, String password, String firstName, String lastName, Role role, Status status, Logins logins) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.status = status;
        this.logins = logins;
    }
}
