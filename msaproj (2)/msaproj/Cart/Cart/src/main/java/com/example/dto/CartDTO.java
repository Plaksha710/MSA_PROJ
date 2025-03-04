package com.example.dto;

public class CartDTO {
    private Long cartId;
    private Long userId;
    private Book book;  // Include book details
    private int quantity;

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CartDTO(Long cartId, Long userId, Book book, int quantity) {
        this.cartId = cartId;
        this.userId = userId;
        this.book = book;
        this.quantity = quantity;
    }

}
