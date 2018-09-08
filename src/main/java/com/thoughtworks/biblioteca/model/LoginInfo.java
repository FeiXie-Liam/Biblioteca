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
public class LoginInfo {
    private String libNum;
    @Builder.Default
    private boolean isLogin = false;
}
