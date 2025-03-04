package com.example.service;

import com.example.entity.Book;
import com.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    // CREATE
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    // READ ALL
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // READ ONE
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    // UPDATE
    public Book updateBook(Long id, Book bookDetails) {
        Optional<Book> existingBook = bookRepository.findById(id);
        if (existingBook.isPresent()) {
            Book book = existingBook.get();
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setPrice(bookDetails.getPrice());
            return bookRepository.save(book);
        }
        return null;
    }

    // DELETE
    public boolean deleteBook(Long id) {
        Optional<Book> existingBook = bookRepository.findById(id);
        if (existingBook.isPresent()) {
            bookRepository.delete(existingBook.get());
            return true;
        }
        return false;
    }
}
