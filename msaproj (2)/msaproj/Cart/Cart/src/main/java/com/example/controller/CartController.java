package com.example.controller;

import com.example.dto.CartDTO;
import com.example.entity.Cart;
import com.example.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{id}/book")
    public ResponseEntity<CartDTO> getCartItemWithBook(@PathVariable Long id) {
        CartDTO cartDTO = cartService.getCartItemWithBookDetails(id);
        return ResponseEntity.ok(cartDTO);
    }

    // ✅ Add an item to the cart
    @PostMapping
    public ResponseEntity<?> addCartItem(@RequestBody Cart cart) {
        try {
            Cart savedCart = cartService.addCartItem(cart);
            return ResponseEntity.ok(savedCart);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ✅ Get all cart items
    @GetMapping
    public ResponseEntity<List<Cart>> getAllCartItems() {
        List<Cart> cartItems = cartService.getAllCartItems();
        return ResponseEntity.ok(cartItems);
    }

    // ✅ Get a cart item by ID
    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartItemById(@PathVariable Long id) {
        Cart cart = cartService.getCartItemById(id);
        return ResponseEntity.ok(cart);
    }

    // ✅ Update a cart item
    @PutMapping("/{id}")
    public ResponseEntity<Cart> updateCartItem(@PathVariable Long id, @RequestBody Cart updatedCart) {
        Cart cart = cartService.updateCartItem(id, updatedCart);
        return ResponseEntity.ok(cart);
    }

    // ✅ Delete a cart item
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long id) {
        cartService.deleteCartItem(id);
        return ResponseEntity.noContent().build();
    }
}
