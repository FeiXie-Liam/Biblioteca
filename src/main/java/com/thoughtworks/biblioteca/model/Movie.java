package com.thoughtworks.biblioteca.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Movie {
    private int id;
    private String name;
    private String director;
    private String publishYear;
    private String rating;
    @Builder.Default
    private boolean checkout = false;

    @Override
    public String toString() {
        return id + ": name:" + name + ", director:" + director + ", publish year:" + publishYear + ", rating:"
                + rating;
    }
}
