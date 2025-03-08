package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.model.StatusLivro;
import com.example.demo.service.BookService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = this.bookService.getBookById(id);

        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book createdBook = this.bookService.createBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBook(@PathVariable Long id) {
        Optional<Book> book = this.bookService.getBookById(id);
        if (book.isPresent()) {
            this.bookService.deleteBook(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping
    public ResponseEntity updateBook(@RequestBody Book book) {
        Book updated = this.bookService.updateBook(book);

        if (updated != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updated);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateStatus(@RequestBody Book book, @PathVariable Long id) {
        if (book.getStatus() != null) {
            Optional<Book> exists = this.bookService.getBookById(id);
            if (exists.isPresent()) {
                exists.ifPresent(livro -> {
                    livro.setStatus(book.getStatus());
                });
                return ResponseEntity.status(HttpStatus.OK).body(exists);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Book>> getByStatus(@PathVariable StatusLivro status) {
        List<Book> books = this.bookService.getBooksByStatus(status);
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }
}
