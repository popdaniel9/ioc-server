package com.onlineshop.onlineshop.entity;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.*;


@Table(name = "comanda")
@Entity
public class ComandaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private OffsetDateTime dataPlasare;

    @Column
    private OffsetDateTime dataSosire;

    @Column
    private Double sumaTotala;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private UserEntity userEntity;

    public ComandaEntity(Long id, OffsetDateTime data, OffsetDateTime dataSosire, Double sumaTotala, UserEntity userEntity) {
        this.id = id;
        this.dataPlasare = data;
        this.dataSosire = dataSosire;
        this.sumaTotala = sumaTotala;
        this.userEntity = userEntity;
    }

    public ComandaEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OffsetDateTime getData() {
        return dataPlasare;
    }

    public void setData(OffsetDateTime data) {
        this.dataPlasare = data;
    }

    public OffsetDateTime getDataSosire() {
        return dataSosire;
    }

    public void setDataSosire(OffsetDateTime dataSosire) {
        this.dataSosire = dataSosire;
    }

    public Double getSumaTotala() {
        return sumaTotala;
    }

    public void setSumaTotala(Double sumaTotala) {
        this.sumaTotala = sumaTotala;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
