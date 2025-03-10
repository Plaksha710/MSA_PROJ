package com.example.dto;

public class Book {
    private Long id;
    private String title;
    private String author;
    private double price;
    private String genre;
    private int stock;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}
