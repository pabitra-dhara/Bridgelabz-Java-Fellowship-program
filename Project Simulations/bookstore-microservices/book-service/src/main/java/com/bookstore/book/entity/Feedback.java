package com.bookstore.book.entity;

import jakarta.persistence.*;

@Entity
@Table(name="feedback")
public class Feedback {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private String email;
    private String comment;
    private int rating;

    public Long getId(){return id;}
    public Long getProductId(){return productId;}
    public void setProductId(Long v){productId=v;}
    public String getEmail(){return email;}
    public void setEmail(String v){email=v;}
    public String getComment(){return comment;}
    public void setComment(String v){comment=v;}
    public int getRating(){return rating;}
    public void setRating(int v){rating=v;}
}
