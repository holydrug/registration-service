package com.popov.registration.service.entity.person.etc;

import com.popov.registration.service.entity.person.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "logins")
public class Logins {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "login_attempt")
    private Long timestamp;

    @OneToOne(mappedBy = "logins")
    private Person person;

    @Column(name = "person_id")
    private Long personId;
    public Logins(Long timestamp) {
        this.timestamp = timestamp;
    }
}
