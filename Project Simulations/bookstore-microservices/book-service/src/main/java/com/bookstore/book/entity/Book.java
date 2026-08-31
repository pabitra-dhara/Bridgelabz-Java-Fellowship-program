package com.bookstore.book.entity;

import jakarta.persistence.*;


@Entity
@Table(name="books")
public class Book {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false) private String bookName;
    private String author;
    @Column(length=2000) private String description;
    private int quantity;
    private double price;
    private double discountPrice;

    public Long getId(){return id;}
    public String getBookName(){return bookName;}
    public void setBookName(String v){bookName=v;}
    public String getAuthor(){return author;}
    public void setAuthor(String v){author=v;}
    public String getDescription(){return description;}
    public void setDescription(String v){description=v;}
    public int getQuantity(){return quantity;}
    public void setQuantity(int v){quantity=v;}
    public double getPrice(){return price;}
    public void setPrice(double v){price=v;}
    public double getDiscountPrice(){return discountPrice;}
    public void setDiscountPrice(double v){discountPrice=v;}
}
