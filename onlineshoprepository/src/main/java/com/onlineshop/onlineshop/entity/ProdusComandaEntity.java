package com.onlineshop.onlineshop.entity;

import javax.persistence.*;

@Table(name = "produs_comanda")
@Entity
public class ProdusComandaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ProdusEntity produsEntity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ComandaEntity comandaEntity;

    @Column
    private Long cantitate;

    @Column
    private Double suma;

    public ProdusComandaEntity(Long id, ProdusEntity produsEntity, ComandaEntity comandaEntity, Long cantitate, Double suma) {
        this.id = id;
        this.produsEntity = produsEntity;
        this.comandaEntity = comandaEntity;
        this.cantitate = cantitate;
        this.suma = suma;
    }

    public ProdusComandaEntity() {
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

    public ComandaEntity getComandaEntity() {
        return comandaEntity;
    }

    public void setComandaEntity(ComandaEntity comandaEntity) {
        this.comandaEntity = comandaEntity;
    }

    public Long getCantitate() {
        return cantitate;
    }

    public void setCantitate(Long cantitate) {
        this.cantitate = cantitate;
    }

    public Double getSuma() {
        return suma;
    }

    public void setSuma(Double suma) {
        this.suma = suma;
    }
}
