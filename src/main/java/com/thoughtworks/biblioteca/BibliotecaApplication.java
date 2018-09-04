package com.thoughtworks.biblioteca;


import com.thoughtworks.biblioteca.service.BibliotecaService;

public class BibliotecaApplication {

	public static void main(String[] args) {
		BibliotecaService bibliotecaService = new BibliotecaService();
		bibliotecaService.mainMenu();
	}
}
