package com.example.session3.repository;

import com.example.session3.model.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthorRepository {

    private final List<Author> authors = new ArrayList<>();

    public AuthorRepository() {
        authors.add(new Author(1, "Admin", "nna@gmail.com"));
        authors.add(new Author(2, "Nam Cao", "namcao@gmail.com"));
        authors.add(new Author(3, "Tô Hoài", "tohoai@gmail.com"));
    }

    public synchronized List<Author> findAll() {
        return new ArrayList<>(authors);
    }

    public synchronized Author findById(int id) {
        return authors.stream()
                .filter(a->a.getId() == id).findFirst().orElse(null);
    }

    public synchronized void save(Author author) {
        authors.add(author);
    }

    public synchronized void delete(int id) {
        authors.removeIf(a->a.getId() != id && a.getId() == id);
    }

}
