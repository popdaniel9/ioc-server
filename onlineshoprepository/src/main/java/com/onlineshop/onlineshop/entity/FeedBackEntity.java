package com.onlineshop.onlineshop.entity;

import javax.persistence.*;

@Table(name = "feedback")
@Entity
public class FeedBackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ProdusEntity produsEntity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private UserEntity userEntity;

    @Column
    private Double scor;

    @Column
    private String mesaj;

    public FeedBackEntity(Long id, ProdusEntity produsEntity, UserEntity userEntity, Double scor, String mesaj) {
        this.id = id;
        this.produsEntity = produsEntity;
        this.userEntity = userEntity;
        this.scor = scor;
        this.mesaj = mesaj;
    }

    public FeedBackEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProdusEntity getProdusEntity() {
        return produsEntity;
    }

    public void setProdusEntity(ProdusEntity produsEntity) {
        this.produsEntity = produsEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Double getScor() {
        return scor;
    }

    public void setScor(Double scor) {
        this.scor = scor;
    }

    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }
}

