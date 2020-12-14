package com.onlineshop.onlineshop.model;

import com.onlineshop.onlineshop.entity.ProdusEntity;

public class ProdusDto {

    private Long id;
    private String nume;
    private Double pret;
    private String descriere;
    private String categorie;
    private String urlPoza;
    private Long cantitate;
    private Long idProducator;
    private String numeProducator;

    public ProdusDto(Long id, String nume, Double pret, String descriere,
                     String categorie, String urlPoza, Long cantitate, Long idProducator,
                     String numeProducator) {
        this.id = id;
        this.nume = nume;
        this.pret = pret;
        this.descriere = descriere;
        this.categorie = categorie;
        this.urlPoza = urlPoza;
        this.cantitate = cantitate;
        this.idProducator = idProducator;
        this.numeProducator = numeProducator;
    }

    public ProdusDto() {
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

    public Long getCantitate() {
        return cantitate;
    }

    public void setCantitate(Long cantitate) {
        this.cantitate = cantitate;
    }

    public Long getIdProducator() {
        return idProducator;
    }

    public void setIdProducator(Long idProducator) {
        this.idProducator = idProducator;
    }

    public String getNumeProducator() {
        return numeProducator;
    }

    public void setNumeProducator(String numeProducator) {
        this.numeProducator = numeProducator;
    }

    public ProdusEntity convertToEntity(){
        ProdusEntity produsEntity = new ProdusEntity();
        produsEntity.setId(this.getId());
        produsEntity.setCantitate(this.getCantitate());
        produsEntity.setCategorie(this.getCategorie());
        produsEntity.setDescriere(this.getDescriere());
        produsEntity.setNume(this.getNume());
        produsEntity.setUrlPoza(this.getUrlPoza());
        produsEntity.setPret(this.getPret());
        return produsEntity;
    }
}
