package com.thoughtworks.biblioteca.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Book {
    private int id;
    private String name;
    private String author;
    private String publishYear;
    private boolean checkout = false;

    @Override
    public String toString() {
        return id + ": name:" + name + ", author:" + author + ", publish year:" + publishYear;
    }
}
