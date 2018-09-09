package com.thoughtworks.biblioteca.service;

import com.thoughtworks.biblioteca.model.Book;
import com.thoughtworks.biblioteca.model.LoginInfo;
import com.thoughtworks.biblioteca.model.Movie;
import com.thoughtworks.biblioteca.model.User;
import com.thoughtworks.biblioteca.repository.BookRepository;
import com.thoughtworks.biblioteca.repository.MovieRepository;
import com.thoughtworks.biblioteca.repository.UserRepository;
import com.thoughtworks.biblioteca.utils.Options;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Scanner;

@Getter
@Setter
public class BibliotecaService {
    private BookRepository bookRepository;
    private MovieRepository movieRepository;
    private UserRepository userRepository;
    private LoginService loginService;
    private Scanner sc;

    public BibliotecaService() {
        bookRepository = new BookRepository();
        movieRepository = new MovieRepository();
        userRepository = new UserRepository();
        loginService = new LoginService();
        sc = new Scanner(System.in);
        System.out.println("Welcome Biblioteca!");
    }

    public void mainMenu() {
        while (true) {
            getMainOptions();

            int option = sc.nextInt();

            switch (option) {
                case 0:
                    if(isLogin()){
                        getUserInfo();
                    }
                    break;
                case 1:
                    listAllBooks();
                    break;
                case 2:
                    listAllMovies();
                    break;
                case 3:
                    if(isLogin()){
                        checkoutBook();
                    }
                    break;
                case 4:
                    if(isLogin()){
                        returnBook();
                    }
                    break;
                case 5:
                    if(isLogin()){
                        checkoutMovie();
                    }
                    break;
                case 6:
                    System.out.println("bye!");
                    return;
                default:
                    System.out.println("Select a valid option!");
            }
        }
    }

    private void getMainOptions() {
        System.out.println("----------");
        Options[] options = {Options.GET_USER_INFO, Options.LIST_ALL_BOOKS, Options.LIST_ALL_MOVIES, Options.CHECKOUT_BOOK, Options
                .RETURN_BOOK, Options.CHECKOUT_MOVIE, Options.QUIT};
        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i].ordinal() + ": " + options[i].name());
        }
    }



    public void listAllBooks() {
        List<Book> books = bookRepository.getValidBooks();
        for (Book book : books) {
            System.out.println(book.toString());
        }
    }

    public void listAllMovies() {
        List<Movie> movies = movieRepository.getValidMovies();
        for (Movie movie : movies) {
            System.out.println(movie.toString());
        }
    }

    public void checkoutBook() {
        System.out.println("Please select checkout book id:");
        int bookId = sc.nextInt();
        boolean succeed = bookRepository.checkoutBook(bookId);
        if (succeed) {
            System.out.println("Thank you! Enjoy the book.");
        } else {
            System.out.println("That book is not available.");
        }
    }

    public void returnBook() {
        System.out.println("Please enter your book id to return:");
        int bookId = sc.nextInt();
        boolean succeed = bookRepository.returnBook(bookId);
        if (succeed) {
            System.out.println("Thank you for returning the book.");
        } else {
            System.out.println("That is not a valid book to return.");
        }
    }

    public void checkoutMovie() {
        System.out.println("Please select checkout movie id:");
        int movieId = sc.nextInt();
        boolean succeed = movieRepository.checkoutMovie(movieId);
        if (succeed) {
            System.out.println("Thank you! Enjoy the movie.");
        } else {
            System.out.println("That movie is not available.");
        }
    }

    public void login() {
        if (!loginService.isLogin()) {
            System.out.println("Please enter your library number:");
            String libNum = sc.next();
            System.out.println("Please enter your password:");
            String password = sc.next();
            checkUser(libNum, password);
        }
    }

    private void checkUser(String libNum, String password) {
        boolean succeed = userRepository.checkUser(libNum, password);
        if(succeed) {
            System.out.println("login succeed!");
            loginService.setLoginInfo(LoginInfo.builder().libNum(libNum).isLogin(true).build());
        }
        else {
            System.out.println("library number or password error!");
        }
    }

    public void getUserInfo() {
        if(isLogin()) {
            String loginLibNum = loginService.getLoginInfo().getLibNum();
            User loginUser = userRepository.findUserByLibNum(loginLibNum);
            System.out.println(loginUser.toString());
        }

    }

    private boolean isLogin() {
        if(!loginService.isLogin()) {
            System.out.println("Please login first:");
            login();
        }
        return loginService.isLogin();
    }
}
