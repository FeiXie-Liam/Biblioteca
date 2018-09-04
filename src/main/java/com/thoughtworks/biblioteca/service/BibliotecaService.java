package com.thoughtworks.biblioteca.service;

import com.thoughtworks.biblioteca.model.Biblioteca;
import com.thoughtworks.biblioteca.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaService {
    private Biblioteca biblioteca;
    public BibliotecaService() {
        createBooks();
        System.out.println("Welcome Biblioteca!");
    }

    private void createBooks() {
        biblioteca = new Biblioteca();
        List<Book> books = new ArrayList<>();
        books.add(new Book("Head First Java"));
        books.add(new Book("Refactor"));
        books.add(new Book("Test-driven Development:By Example"));
        biblioteca.setBooks(books);
    }

    public void listAllBooks() {
        biblioteca.getBooks().forEach(book -> System.out.println(book.toString()));
    }
}
