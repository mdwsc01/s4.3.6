package com.example.session3.service;

import com.example.session3.model.Author;
import com.example.session3.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthor() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(int id) {
        return authorRepository.findById(id);
    }

    public Author createAuthor(Author author) {
        authorRepository.save(author);
        return author;
    }

    public boolean deleteAuthor(int id) {
        Author author = authorRepository.findById(id);
        if (author == null) {
            return false;
        }
        if("Admin".equalsIgnoreCase(author.getName())) {
            throw new IllegalArgumentException(" Không được phép xóa tài khoản Admin");
        }

        authorRepository.delete(id);
        return true;
    }

    public List<Author> searchAuthors(String keyword){
        List<Author> result = new ArrayList<>();

        for(Author authors: authorRepository.findAll()){
            if(authors.getName() != null &&
                    authors.getName().toLowerCase(Locale.ROOT).contains(keyword.toLowerCase(Locale.ROOT))){
                result.add(authors);
            }
        }
        return result;
    }
}