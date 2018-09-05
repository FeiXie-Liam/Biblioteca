package com.thoughtworks.biblioteca.service;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import com.thoughtworks.biblioteca.model.Biblioteca;
import com.thoughtworks.biblioteca.model.Book;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BibliotecaServiceTest {
    PrintStream console = null;
    ByteOutputStream bytes = null;

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
        BibliotecaService bibliotecaService = new BibliotecaService();
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
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        BibliotecaService bibliotecaService = new BibliotecaService();
        //when
        bibliotecaService.mainMenu();
        String actual = bytes.toString();
        //then
        assertThat(actual).contains("List Books");
    }

    @Test
    public void should_notify_invalid_option() {
        //given
        ByteArrayInputStream in = new ByteArrayInputStream("4".getBytes());
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
        ByteArrayInputStream in = new ByteArrayInputStream("1\n2".getBytes());
        System.setIn(in);
        BibliotecaService bibliotecaService = new BibliotecaService();
        //when
        bibliotecaService.mainMenu();
        String actual = bytes.toString();
        //then
        assertThat(actual).contains("bye!");
    }
}