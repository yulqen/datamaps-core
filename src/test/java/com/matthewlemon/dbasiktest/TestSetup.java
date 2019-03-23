package com.matthewlemon.dbasiktest;

import com.matthewlemon.dbasiktest.doubles.InMemoryUserGateway;
import com.matthewlemon.dbasiktest.entities.User;

public class TestSetup {
    public static void setupContext() {
        Context.userGateway = new InMemoryUserGateway();
    }

    public static void setUpSampleData() {
        setupContext();

        User matt = new User("Matt");

        Context.userGateway.save(matt);
    }
}
