package com.popov.registration.service.entity.animals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "animals", uniqueConstraints = @UniqueConstraint(columnNames={"nickname"}))
public class Animal {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "kind")
    private Kind kind;
    @Column(name = "data_birth")
    private String dataBirth;
    @Column(name = "sex")
    private String sex;
    @Column(name = "nickname")
    private String nickname;
}
