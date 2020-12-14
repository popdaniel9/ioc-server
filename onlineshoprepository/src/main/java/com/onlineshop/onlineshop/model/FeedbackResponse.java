package com.onlineshop.onlineshop.model;

import java.util.Objects;

public class FeedbackResponse {

    private Long id;

    public FeedbackResponse(Long id, Long idProduct, Double score, String message, String username) {
        this.id = id;
        this.idProduct = idProduct;
        this.score = score;
        this.message = message;
        this.username = username;
    }

    private Long idProduct;
    private Double score;
    private String message;
    private String username;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedbackResponse that = (FeedbackResponse) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(idProduct, that.idProduct) &&
                Objects.equals(score, that.score) &&
                Objects.equals(message, that.message) &&
                Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idProduct, score, message, username);
    }

    @Override
    public String toString() {
        return "FeedbackResponse{" +
                "id=" + id +
                ", idProduct=" + idProduct +
                ", score=" + score +
                ", message='" + message + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
