package com.thoughtworks.biblioteca.repository;

import com.thoughtworks.biblioteca.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepository {
    private List<User> users;

    public UserRepository() {
        initUsers();
    }
    private void initUsers() {
        users = new ArrayList<>();
        users.add(User
                .builder()
                .libNum("111-2222")
                .password("1234")
                .name("zhangsan")
                .email("zhangsan@qq.com")
                .phoneNum("12345678910")
                .build()
        );
        users.add(User
                .builder()
                .libNum("123-3333")
                .password("2345")
                .name("lisi")
                .email("lisi@qq.com")
                .phoneNum("12345678911")
                .build()
        );
    }

    public boolean checkUser(String libNum, String password) {
        int size = users
                .stream()
                .filter(user ->
                        user.getLibNum().equals(libNum) &&
                                user.getPassword().equals(password))
                .collect(Collectors.toList())
                .size();
        if (size == 0) {
            return false;
        }
        return true;
    }

    public User findUserByLibNum(String loginLibNum) {
        return users
                .stream()
                .filter(user -> user.getLibNum().equals(loginLibNum))
                .collect(Collectors.toList()).get(0);
    }
}

