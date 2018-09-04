package com.thoughtworks.biblioteca.service;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

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
        String expected = "Head First Java\nRefactor\nTest-driven Development:By Example\n";
        //when
        bibliotecaService.listAllBooks();

        String actual = bytes.toString();
        //then
        assertThat(actual).contains(expected);
    }
}