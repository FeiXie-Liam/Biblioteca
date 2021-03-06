package com.thoughtworks.biblioteca.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    private String libNum;
    private String password;
    private String name;
    private String email;
    private String phoneNum;

    @Override
    public String toString() {
        return String.format("name: %s, email: %s, phoneNum: %s", name, email, phoneNum);
    }
}
