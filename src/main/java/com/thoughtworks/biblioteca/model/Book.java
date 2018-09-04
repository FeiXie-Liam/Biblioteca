package com.thoughtworks.biblioteca.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Book {
    private String name;
    private String author;
    private String publishYear;

    @Override
    public String toString() {
        return "name:" + name + ", author:" + author + ", publish year:" + publishYear;
    }
}
