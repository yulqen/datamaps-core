package com.matthewlemon.dbasiktest;

import com.matthewlemon.dbasiktest.doubles.InMemoryUserGateway;

public class TestSetup {
    public static void setupContext() {
        Context.userGateway = new InMemoryUserGateway();
    }
}
