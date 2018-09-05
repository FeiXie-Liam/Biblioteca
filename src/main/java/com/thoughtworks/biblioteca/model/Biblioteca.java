package com.thoughtworks.biblioteca.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Biblioteca {
    List<Book> books;

    public List<Book> getBooks() {
        return books.stream().filter(book -> !book.isCheckout()).collect(Collectors.toList());
    }

    public boolean returnBook(int bookId) {
        List<Book> selectedBook = books.stream().filter(book -> book.getId() == bookId && book.isCheckout()).collect(Collectors.toList());
        if(selectedBook.size() == 0){
            return false;
        }
        books.forEach(book -> {
            if(book.getId()==bookId){
                book.setCheckout(false);
            }
        });
        return true;
    }

    public boolean checkoutBook(int bookId) {
        List<Book> selectedBook = books.stream().filter(book -> book.getId() == bookId && !book.isCheckout()).collect(Collectors.toList());
        if(selectedBook.size() == 0){
            return false;
        }
        books.forEach(book -> {
            if(book.getId()==bookId){
                book.setCheckout(true);
            }
        });
        return true;
    }
}
