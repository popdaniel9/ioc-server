package com.onlineshop.onlineshop.entity;

import com.onlineshop.onlineshop.model.ProdusDto;

import javax.persistence.*;

@Table(name = "produs")
@Entity
public class ProdusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nume;

    @Column
    private Double pret;

    @Column
    private String descriere;

    @Column
    private String categorie;

    @Column
    private String urlPoza;

    @Column
    private Long cantitate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ProducatorEntity producatorEntity;


    public ProdusEntity(Long id, String nume, Double pret, String descriere, String categorie, String urlPoza, Long cantitate, ProducatorEntity producatorEntity) {
        this.id = id;
        this.nume = nume;
        this.pret = pret;
        this.descriere = descriere;
        this.categorie = categorie;
        this.urlPoza = urlPoza;
        this.producatorEntity = producatorEntity;
        this.cantitate = cantitate;
    }

    public ProdusEntity() {
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

    public Double getPret() {
        return pret;
    }

    public void setPret(Double pret) {
        this.pret = pret;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getUrlPoza() {
        return urlPoza;
    }

    public void setUrlPoza(String urlPoza) {
        this.urlPoza = urlPoza;
    }

    public ProducatorEntity getProducatorEntity() {
        return producatorEntity;
    }

    public void setProducatorEntity(ProducatorEntity producatorEntity) {
        this.producatorEntity = producatorEntity;
    }

    public Long getCantitate() {
        return cantitate;
    }

    public void setCantitate(Long cantitate) {
        this.cantitate = cantitate;
    }

    public ProdusDto convertToDto() {
        return new ProdusDto(id, nume, pret, descriere, categorie, urlPoza, cantitate, producatorEntity.getId(), producatorEntity.getNume());

    }
}
