package com.thoughtworks.biblioteca.service;

import com.thoughtworks.biblioteca.model.Biblioteca;
import com.thoughtworks.biblioteca.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BibliotecaService {
    private Biblioteca biblioteca;
    private Scanner sc;

    public BibliotecaService() {
        createBooks();
        sc = new Scanner(System.in);
        System.out.println("Welcome Biblioteca!");
    }

    public void mainMenu() {
        String options = "----------\n1. List Books";
        System.out.println(options);

        int option = sc.nextInt();

        switch (option) {
            case 1:
                listAllBooks();
                break;
            default:
                System.out.println("Select a valid option!");
        }
    }

    private void createBooks() {
        biblioteca = new Biblioteca();
        List<Book> books = new ArrayList<>();
        books.add(Book
                .builder()
                .name("Head First Java")
                .author("Bert Bates")
                .publishYear("2005")
                .build()
        );
        books.add(Book
                .builder()
                .name("Refactoring")
                .author("Martin Fowler")
                .publishYear("2003")
                .build()
        );
        books.add(Book
                .builder()
                .name("Test-driven Development:By Example")
                .author("Kent Beck")
                .publishYear("2004")
                .build()
        );
        biblioteca.setBooks(books);
    }

    public void listAllBooks() {
        biblioteca.getBooks().forEach(book -> System.out.println(book.toString()));
    }
}
