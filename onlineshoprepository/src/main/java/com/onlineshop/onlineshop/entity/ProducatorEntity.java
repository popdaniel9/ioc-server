package com.onlineshop.onlineshop.entity;

import javax.persistence.*;
import java.util.*;

@Table(name = "producator")
@Entity
public class ProducatorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nume;

    @Column
    private String numarTelefon;

    public ProducatorEntity(Long id, String nume, String numarTelefon) {
        this.id = id;
        this.nume = nume;
        this.numarTelefon = numarTelefon;
    }


    public ProducatorEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getNumarTelefon() {
        return numarTelefon;
    }

    public void setNumarTelefon(String numarTelefon) {
        this.numarTelefon = numarTelefon;
    }

}
