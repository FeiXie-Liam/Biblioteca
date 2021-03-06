package com.thoughtworks.biblioteca.service;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import com.thoughtworks.biblioteca.model.Book;
import com.thoughtworks.biblioteca.utils.Options;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BibliotecaServiceTest {
    private final String ERROR_PASSWORD = "12345678";
    private final String PREDEFINED_LIB_NUM = "111-2222";
    private final String PREDEFINED_PASSWORD = "1234";
    private ByteOutputStream bytes = null;
    private final int INVALID_OPTION = 8;
    private final int VALID_CHECKOUT = 1;
    private final int VALID_RETURN_ID = 3;

    @Before
    public void setUp() {
        bytes = new ByteOutputStream();
        System.setOut(new PrintStream(bytes));
    }

    @Test
    public void should_print_hello_when_biblioteca_service_is_available() {
        //given
        String expected = "Welcome Biblioteca!\n";

        //when
        BibliotecaService bibliotecaService = new BibliotecaService();
        String actual = bytes.toString();
        //then
        assertThat(actual).isEqualTo(expected);

    }

    @Test
    public void should_list_all_books() {
        //given
        String input = Options.LIST_ALL_BOOKS.ordinal() + "\n" + Options.QUIT.ordinal();
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        BibliotecaService bibliotecaService = new BibliotecaService();
        String expected_book1 = "Head First Java";
        String expected_book2 = "Refactor";
        String expected_book3 = "Test-driven Development:By Example";
        //when
        bibliotecaService.listAllBooks();
        String actual = bytes.toString();
        //then
        assertThat(actual).contains(expected_book1);
        assertThat(actual).contains(expected_book2);
        assertThat(actual).contains(expected_book3);
    }

    @Test
    public void should_list_all_books_details() {
        //given
        String input = Options.LIST_ALL_BOOKS.ordinal() + "\n" + Options.QUIT.ordinal();
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        BibliotecaService bibliotecaService = new BibliotecaService();
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
        String expectedBookDetail1 = books.get(0).toString();
        String expectedBookDetail2 = books.get(1).toString();
        String expectedBookDetail3 = books.get(2).toString();
        //when
        bibliotecaService.listAllBooks();
        String actual = bytes.toString();
        //then
        assertThat(actual).contains(expectedBookDetail1);
        assertThat(actual).contains(expectedBookDetail2);
        assertThat(actual).contains(expectedBookDetail3);
    }

    @Test
    public void should_access_main_menu() {
        //given
        String input = Options.LIST_ALL_BOOKS.ordinal() + "\n" + Options.QUIT.ordinal();
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        BibliotecaService bibliotecaService = new BibliotecaService();
        //when
        bibliotecaService.mainMenu();
        String actual = bytes.toString();
        //then
        assertThat(actual).contains("LIST_ALL_BOOKS");
    }

    @Test
    public void should_notify_invalid_option() {
        //given
        String input = INVALID_OPTION + "\n" + Options.QUIT.ordinal();
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        BibliotecaService bibliotecaService = new BibliotecaService();
        //when
        bibliotecaService.mainMenu();
        String actual = bytes.toString();
        //then
        assertThat(actual).contains("Select a valid option!");
    }

    @Test
    public void should_continue_until_choose_quit() {
        //given
        String input = Options.LIST_ALL_BOOKS.ordinal() + "\n" + Options.QUIT.ordinal();
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        BibliotecaService bibliotecaService = new BibliotecaService();
        //when
        bibliotecaService.mainMenu();
        String actual = bytes.toString();
        //then
        assertThat(actual).contains("bye!");
    }

    @Test
    public void should_remove_book_when_checkout() {
        //given
        String input = String.valueOf(VALID_CHECKOUT);
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        BibliotecaService bibliotecaService = new BibliotecaService();
        int originSize = bibliotecaService.getBookRepository().getValidBooks().size();
        //when
        bibliotecaService.checkoutBook();
        //then
        assertThat(bibliotecaService.getBookRepository().getValidBooks().size()).isEqualTo(originSize - 1);
    }

    @Test
    public void should_checkout_successful() {
        //given
        String input = VALID_CHECKOUT + "\n" + Options.QUIT.ordinal();
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        BibliotecaService bibliotecaService = new BibliotecaService();
        int originSize = bibliotecaService.getBookRepository().getValidBooks().size();
        //when
        bibliotecaService.checkoutBook();
        String actual = bytes.toString();
        //then
        assertThat(bibliotecaService.getBookRepository().getValidBooks().size()).isEqualTo(originSize - 1);
        assertThat(actual).contains("Thank you! Enjoy the book");
    }

    @Test
    public void should_notify_customer_when_checkout_failure() {
        //given
        String input = INVALID_OPTION + "\n" + Options.QUIT.ordinal();
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        BibliotecaService bibliotecaService = new BibliotecaService();
        //when
        bibliotecaService.checkoutBook();
        String actual = bytes.toString();
        //then
        assertThat(actual).contains("That book is not available.");
    }

    @Test
    public void should_add_book_when_customer_return_book() {
        //given
        String input = String.valueOf(VALID_RETURN_ID);
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        BibliotecaService bibliotecaService = new BibliotecaService();
        int originSize = bibliotecaService.getBookRepository().getValidBooks().size();
        //when
        bibliotecaService.returnBook();
        //then
        assertThat(bibliotecaService.getBookRepository().getValidBooks().size()).isEqualTo(originSize + 1);
    }

    @Test
    public void should_notify_customer_return_success() {
        String input = String.valueOf(VALID_RETURN_ID);
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        BibliotecaService bibliotecaService = new BibliotecaService();
        //when
        bibliotecaService.returnBook();
        String actual = bytes.toString();
        //then
        assertThat(actual).contains("Thank you for returning the book.");
    }

    @Test
    public void should_notify_customer_return_failure() {
        String input = String.valueOf(INVALID_OPTION);
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        BibliotecaService bibliotecaService = new BibliotecaService();
        //when
        bibliotecaService.returnBook();
        String actual = bytes.toString();
        //then
        assertThat(actual).contains("That is not a valid book to return.");
    }

    @Test
    public void should_list_all_movies() {
        //given
        String input = Options.LIST_ALL_MOVIES.ordinal() + "\n" + Options.QUIT.ordinal();
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        BibliotecaService bibliotecaService = new BibliotecaService();

        String expectedMovieDetail1 = "0: name:肖生克的救赎, director:Frank Darabont, publish year:1994, rating:9.6";
        String expectedMovieDetail2 = "1: name:霸王别姬, director:陈凯歌, publish year:1993, rating:9.5";
        String expectedMovieDetail3 = "2: name:这个杀手不太冷, director:Luc Besson, publish year:1994, rating:9.4";
        //when
        bibliotecaService.listAllMovies();
        String actual = bytes.toString();
        //then
        assertThat(actual).contains(expectedMovieDetail1);
        assertThat(actual).contains(expectedMovieDetail2);
        assertThat(actual).contains(expectedMovieDetail3);
    }

    @Test
    public void should_remove_movie_when_checkout() {
        //given
        String input = String.valueOf(VALID_CHECKOUT);
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        BibliotecaService bibliotecaService = new BibliotecaService();
        int originSize = bibliotecaService.getMovieRepository().getValidMovies().size();
        //when
        bibliotecaService.checkoutMovie();
        //then
        assertThat(bibliotecaService.getMovieRepository().getValidMovies().size()).isEqualTo(originSize - 1);
    }

    @Test
    public void should_login_success() {
        //given
        String input = PREDEFINED_LIB_NUM + "\n" + PREDEFINED_PASSWORD;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        BibliotecaService bibliotecaService = new BibliotecaService();
        //when
        bibliotecaService.login();
        String actual = bytes.toString();
        //then
        assertThat(actual).contains("Please enter your library number:");
        assertThat(actual).contains("Please enter your password:");
        assertThat(actual).contains("login succeed!");
    }

    @Test
    public void should_login_failure() {
        //given
        String input = PREDEFINED_LIB_NUM + "\n" + ERROR_PASSWORD;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        BibliotecaService bibliotecaService = new BibliotecaService();
        //when
        bibliotecaService.login();
        String actual = bytes.toString();
        //then
        assertThat(actual).contains("Please enter your library number:");
        assertThat(actual).contains("Please enter your password:");
        assertThat(actual).contains("library number or password error!");
    }

    @Test
    public void should_login_before_see_user_info() {
        //given
        String input = Options.GET_USER_INFO.ordinal() + "\n" + PREDEFINED_LIB_NUM + "\n" + ERROR_PASSWORD;
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        BibliotecaService bibliotecaService = new BibliotecaService();
        //when
        bibliotecaService.getUserInfo();
        String actual = bytes.toString();
        //then
        assertThat(actual).contains("Please login first:");
    }
}