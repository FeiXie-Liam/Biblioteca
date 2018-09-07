package com.thoughtworks.biblioteca.service;

import com.thoughtworks.biblioteca.model.Biblioteca;
import com.thoughtworks.biblioteca.model.Book;
import com.thoughtworks.biblioteca.model.Movie;
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
    private boolean isLogin = false;

    public BibliotecaService() {
        biblioteca = new Biblioteca();
        init();
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
                    listAllMovies();
                    break;
                case 2:
                    checkoutBook();
                    break;
                case 3:
                    returnBook();
                    break;
                case 4:
                    checkoutMovie();
                    break;
                case 5:
                    System.out.println("bye!");
                    return;
                default:
                    System.out.println("Select a valid option!");
            }
        }
    }

    private void getMainOptions() {
        System.out.println("----------");
        Options[] options = {Options.LIST_ALL_BOOKS, Options.LIST_ALL_MOVIES, Options.CHECKOUT_BOOK, Options
                .RETURN_BOOK, Options.CHECKOUT_MOVIE, Options.QUIT};
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i].ordinal() + ": " + options[i].name());
        }
    }

    private void init() {
        biblioteca.init();
    }


    public void listAllBooks() {
        List<Book> books = biblioteca.getValidBooks();
        for (Book book : books) {
            System.out.println(book.toString());
        }
    }

    public void listAllMovies() {
        List<Movie> movies = biblioteca.getValidMovies();
        for (Movie movie : movies) {
            System.out.println(movie.toString());
        }
    }

    public void checkoutBook() {
        System.out.println("Please select checkout book id:");
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

    public void checkoutMovie() {
        System.out.println("Please select checkout movie id:");
        int movieId = sc.nextInt();
        boolean succeed = biblioteca.checkoutMovie(movieId);
        if (succeed) {
            System.out.println("Thank you! Enjoy the movie.");
        } else {
            System.out.println("That movie is not available.");
        }
    }

    public void login() {
        if (!isLogin) {
            System.out.println("Please enter your library number:");
            String libNum = sc.next();
            System.out.println("Please enter your password:");
            String password = sc.next();
            checkUser(libNum, password);
        }
    }

    private void checkUser(String libNum, String password) {
        boolean succeed = biblioteca.checkUser(libNum, password);
        if(succeed) {
            System.out.println("login succeed!");
            isLogin = true;
        }
        else {
            System.out.println("library number or password error!");
        }
    }
}
