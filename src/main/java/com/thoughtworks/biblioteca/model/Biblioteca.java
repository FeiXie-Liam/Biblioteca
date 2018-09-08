package com.thoughtworks.biblioteca.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Biblioteca {
    private List<Book> books;
    private List<Movie> movies;
    private List<User> users;

    private LoginInfo loginInfo;

    public List<Book> getValidBooks() {
        return books.stream().filter(book -> !book.isCheckout()).collect(Collectors.toList());
    }

    public List<Movie> getValidMovies() {
        return movies.stream().filter(movie -> !movie.isCheckout()).collect(Collectors.toList());
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

    public void init() {
        initBooks();
        initMovies();
        initUsers();
        initLoginStatus();
    }

    private void initLoginStatus() {
        loginInfo = LoginInfo.builder().build();
    }

    private void initUsers() {
        users = new ArrayList<>();
        users.add(User
                .builder()
                .libNum("111-2222")
                .password("1234")
                .name("zhangsan")
                .email("zhangsan@qq.com")
                .phoneNum("12345678910")
                .build()
        );
        users.add(User
                .builder()
                .libNum("123-3333")
                .password("2345")
                .name("lisi")
                .email("lisi@qq.com")
                .phoneNum("12345678911")
                .build()
        );
    }

    private void initMovies() {
        movies = new ArrayList<>();
        movies.add(Movie
                .builder()
                .id(0)
                .name("肖生克的救赎")
                .director("Frank Darabont")
                .publishYear("1994")
                .rating("9.6")
                .build()
        );
        movies.add(Movie
                .builder()
                .id(1)
                .name("霸王别姬")
                .director("陈凯歌")
                .publishYear("1993")
                .rating("9.5")
                .build()
        );
        movies.add(Movie
                .builder()
                .id(2)
                .name("这个杀手不太冷")
                .director("Luc Besson")
                .publishYear("1994")
                .rating("9.4")
                .build()
        );
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

    public boolean checkoutMovie(int movieId) {
        List<Movie> selectedMovie = movies.stream().filter(movie -> movie.getId() == movieId && !movie.isCheckout())
                .collect
                (Collectors.toList());
        if (selectedMovie.size() == 0) {
            return false;
        }
        movies.forEach(movie -> {
            if (movie.getId() == movieId) {
                movie.setCheckout(true);
            }
        });
        return true;
    }

    public boolean checkUser(String libNum, String password) {
        int size = users
                .stream()
                .filter(user ->
                        user.getLibNum().equals(libNum) &&
                                user.getPassword().equals(password))
                .collect(Collectors.toList())
                .size();
        if (size == 0) {
            return false;
        }
        return true;
    }

    public boolean isLogin() {
        return loginInfo.isLogin();
    }

    public User findUserByLibNum(String loginLibNum) {
        return users
                .stream()
                .filter(user -> user.getLibNum().equals(loginLibNum))
                .collect(Collectors.toList()).get(0);
    }
}
