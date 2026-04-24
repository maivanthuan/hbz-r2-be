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

import com.hbz.hbzr2.model.Author;
import com.hbz.hbzr2.repository.AuthorRepository;

@RestController
@RequestMapping("/api/authors")
@CrossOrigin(origins = "*")
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;
    @GetMapping
    public Page<Author> getAuthor(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size)
    {
        return authorRepository.findAllByOrderByIdAsc(PageRequest.of(page, size));
    }
    @PostMapping
    public Author createAuthor(@RequestBody Author author){
        return authorRepository.save(author);
    }
    @PutMapping("/{id}")
    public Author updateAuthor(@PathVariable Long id, @RequestBody Author newAuthor){
        Author author = authorRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Khong tim thay"));
        author.setName(newAuthor.getName());
        return authorRepository.save(author);
    }
    @GetMapping("/all")
    public List<Author> getAll(){
        return authorRepository.findAll();
    }
    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id){
        authorRepository.deleteById(id);
    }


}
