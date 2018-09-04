package com.thoughtworks.biblioteca.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {
    private String name;

    @Override
    public String toString() {
        return name;
    }
}
