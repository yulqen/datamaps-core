package com.matthewlemon.dbasiktest.doubles;

import com.matthewlemon.dbasiktest.entities.User;

import java.util.ArrayList;
import java.util.List;

public class InMemoryUserGateway implements com.matthewlemon.dbasiktest.gateways.UserGateway {

    private List<User> users = new ArrayList<>();

    @Override
    public User save(User user) {
        users.add(user);
        return user;
    }
}
