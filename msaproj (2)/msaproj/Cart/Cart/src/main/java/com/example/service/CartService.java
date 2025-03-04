package com.example.service;

import com.example.dto.Book;
import com.example.dto.CartDTO;
import com.example.entity.Cart;
import com.example.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository repository;

    @Autowired
    private RestTemplate restTemplate;  // 🔹 Inject RestTemplate to call BookService

    private static final String BOOK_SERVICE_URL = "http://localhost:8081/books"; // Replace with BookService URL

    private static final String USER_SERVICE_URL = "http://localhost:8084/users";  // User MS

    // ✅ Add item to cart (Validate user from User MS and check book stock from Book MS)
    public Cart addCartItem(Cart cart) {
        // 🔹 Step 1: Check if the user exists in User MS
        String userUrl = USER_SERVICE_URL + "/" + cart.getUserId()+ "/exists";
        Boolean userExists = restTemplate.getForObject(userUrl, Boolean.class);

        if (!Boolean.TRUE.equals(userExists)) {
            throw new RuntimeException("User not found in UserService");
        }

        // 🔹 Step 2: Check if the book exists in Book MS
        String bookUrl = BOOK_SERVICE_URL + "/" + cart.getBookId();
        Book book = restTemplate.getForObject(bookUrl, Book.class);

        if (book == null) {
            throw new RuntimeException("Book not found in BookService");
        }
        if (book.getStock() < cart.getQuantity()) {
            throw new RuntimeException("Not enough stock available");
        }

        return repository.save(cart);
    }

    // ✅ Get all cart items
    public List<Cart> getAllCartItems() {
        return repository.findAll();
    }

    // ✅ Get cart item by ID
    public Cart getCartItemById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
    }

    // ✅ Update cart item
    public Cart updateCartItem(Long id, Cart updatedCart) {
        Cart existingCart = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        existingCart.setBookId(updatedCart.getBookId());
        existingCart.setQuantity(updatedCart.getQuantity());

        return repository.save(existingCart);
    }

    // ✅ Delete cart item
    public void deleteCartItem(Long id) {
        repository.deleteById(id);
    }

    public CartDTO getCartItemWithBookDetails(Long id) {
        // Fetch the cart item from the database
        Cart cart = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        // Call Book Microservice to get book details
        String bookUrl = BOOK_SERVICE_URL + "/" + cart.getBookId();
        Book book = restTemplate.getForObject(bookUrl, Book.class);

        if (book == null) {
            throw new RuntimeException("Book details not available");
        }

        // Construct a CartDTO to include book details
        return new CartDTO(cart.getCartId(), cart.getUserId(), book, cart.getQuantity());
    }

}
