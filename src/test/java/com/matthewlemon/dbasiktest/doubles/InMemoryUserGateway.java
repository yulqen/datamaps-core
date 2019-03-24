package com.matthewlemon.dbasiktest.doubles;

import com.matthewlemon.dbasiktest.entities.User;
import com.matthewlemon.dbasiktest.gateways.UserGateway;

import java.util.ArrayList;
import java.util.List;

public class InMemoryUserGateway implements UserGateway {

    private List<User> users = new ArrayList<>();

    @Override
    public User save(User user) {
        users.add(user);
        return user;
    }

    @Override
    public boolean userExists(User user) {
        return users.contains(user);
    }
}
