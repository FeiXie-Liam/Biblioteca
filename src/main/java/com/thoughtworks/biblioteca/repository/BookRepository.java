package com.thoughtworks.biblioteca.repository;

import com.thoughtworks.biblioteca.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookRepository {
    private List<Book> books;

    public BookRepository() {
        initBooks();
    }

    public List<Book> getValidBooks() {
        return books.stream().filter(book -> !book.isCheckout()).collect(Collectors.toList());
    }

    private void initBooks() {
        books = new ArrayList<>();
        books.add(Book
                .builder()
                .id(0)
                .name("Head First Java")
                .author("Bert Bates")
                .publishYear("2005")
                .build()
        );
        books.add(Book
                .builder()
                .id(1)
                .name("Refactoring")
                .author("Martin Fowler")
                .publishYear("2003")
                .build()
        );
        books.add(Book
                .builder()
                .id(2)
                .name("Test-driven Development:By Example")
                .author("Kent Beck")
                .publishYear("2004")
                .build()
        );
        books.add(Book
                .builder()
                .id(3)
                .name("C++ primer")
                .author("Stanley B. Lippman")
                .publishYear("2013")
                .checkout(true)
                .build()
        );
    }

    public boolean checkoutBook(int bookId) {
        List<Book> selectedBook = books.stream().filter(book -> book.getId() == bookId && !book.isCheckout()).collect
                (Collectors.toList());
        if (selectedBook.size() == 0) {
            return false;
        }
        books.forEach(book -> {
            if (book.getId() == bookId) {
                book.setCheckout(true);
            }
        });
        return true;
    }

    public boolean returnBook(int bookId) {
        List<Book> selectedBook = books.stream().filter(book -> book.getId() == bookId && book.isCheckout()).collect
                (Collectors.toList());
        if (selectedBook.size() == 0) {
            return false;
        }
        books.forEach(book -> {
            if (book.getId() == bookId) {
                book.setCheckout(false);
            }
        });
        return true;
    }
}
