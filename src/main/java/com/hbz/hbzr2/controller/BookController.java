package com.hbz.hbzr2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hbz.hbzr2.model.Book;
import com.hbz.hbzr2.repository.BookRepository;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")
public class BookController {
    @Autowired
    private BookRepository bookRepository;
    @GetMapping
    public Page<Book> getBook(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size)
    {
        return bookRepository.findAllByOrderByIdAsc(PageRequest.of(page, size));
    }
    @PostMapping
    public Book createBook(@RequestBody Book book){
        return bookRepository.save(book);
    }
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book newBook){
        Book book = bookRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Khong tim thay"));
        book.setTitle(newBook.getTitle());
        return bookRepository.save(book);
    }
    @GetMapping("/all")
    public List<Book> getAll(){
        return bookRepository.findAll();
    }
    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id){
        bookRepository.deleteById(id);
    }
}
