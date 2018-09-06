package com.thoughtworks.biblioteca.service;

import com.thoughtworks.biblioteca.model.Biblioteca;
import com.thoughtworks.biblioteca.model.Book;
import com.thoughtworks.biblioteca.utils.Options;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Scanner;

@Getter
@Setter
public class BibliotecaService {
    private Biblioteca biblioteca;
    private Scanner sc;

    public BibliotecaService() {
        createBooks();
        sc = new Scanner(System.in);
        System.out.println("Welcome Biblioteca!");
    }

    public void mainMenu() {
        while (true) {
            getMainOptions();

            int option = sc.nextInt();

            switch (option) {
                case 0:
                    listAllBooks();
                    break;
                case 1:
                    checkoutBook();
                    break;
                case 2:
                    returnBook();
                    break;
                case 3:
                    System.out.println("bye!");
                    return;
                default:
                    System.out.println("Select a valid option!");
            }
        }
    }

    private void getMainOptions() {
        System.out.println("----------");
        Options[] options = {Options.LIST_ALL_BOOKS, Options.CHECKOUT_BOOK, Options.RETURN_BOOK, Options.QUIT};
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i].ordinal() + ": " + options[i].name());
        }
    }

    private void createBooks() {
        biblioteca = new Biblioteca();
        biblioteca.initBooks();
    }

    public void listAllBooks() {
        List<Book> books = biblioteca.getValidBooks();
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).toString());
        }
    }

    public void checkoutBook() {
        System.out.println("Please select checkoutBook book id:");
        int bookId = sc.nextInt();
        boolean succeed = biblioteca.checkoutBook(bookId);
        if (succeed) {
            System.out.println("Thank you! Enjoy the book.");
        } else {
            System.out.println("That book is not available.");
        }
    }

    public void returnBook() {
        System.out.println("Please enter your book id to return:");
        int bookId = sc.nextInt();
        boolean succeed = biblioteca.returnBook(bookId);
        if (succeed) {
            System.out.println("Thank you for returning the book.");
        } else {
            System.out.println("That is not a valid book to return.");
        }
    }
}
