package com.thoughtworks.biblioteca.service;

import com.thoughtworks.biblioteca.model.Biblioteca;
import com.thoughtworks.biblioteca.model.Book;
import com.thoughtworks.biblioteca.utils.Options;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
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

                case 2:
                    System.out.println("bye!");
                    return;
                default:
                    System.out.println("Select a valid option!");
            }
        }
    }

    private void getMainOptions() {
        System.out.println("----------");
        Options[] options = {Options.LIST_ALL_BOOKS, Options.RETURN_BOOK, Options.QUIT};
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i].ordinal()+": "+options[i].name());
        }
    }

    private void createBooks() {
        biblioteca = new Biblioteca();
        List<Book> books = new ArrayList<>();
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
                .name("Test-driven Development:By Example")
                .author("Kent Beck")
                .publishYear("2004")
                .checkout(true)
                .build()
        );
        biblioteca.setBooks(books);
    }

    public void listAllBooks() {
        List<Book> books = biblioteca.getBooks();
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i).toString());
        }
        int option = sc.nextInt();
        checkout(option);
    }

    public Book checkout(int index) {
        List<Book> books = biblioteca.getBooks();
        if(index>=books.size()||index<0){
            System.out.println("That book is not available.");
            return null;
        }
        Book checkoutBook = books.get(index);
        checkoutBook.setCheckout(true);
        System.out.println("Thank you! Enjoy the book");
        return checkoutBook;
    }

    public void returnBook() {
        int bookId = sc.nextInt();
        boolean succeed = biblioteca.returnBook(bookId);
        if(succeed){
            System.out.println("Thank you for returning the book.");
        }
    }
}
