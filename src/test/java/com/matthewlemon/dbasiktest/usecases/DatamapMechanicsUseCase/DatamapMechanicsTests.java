package com.matthewlemon.dbasiktest.usecases.DatamapMechanicsUseCase;

import com.matthewlemon.dbasiktest.Context;
import com.matthewlemon.dbasiktest.TestSetup;
import com.matthewlemon.dbasiktest.entities.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DatamapMechanicsTests {

    @Before
    public void setUp() {
        TestSetup.setupContext();
    }

    @Test
    public void canCreateNewUser() {
        User user = new User("Titface");
        Context.userGateway.save(user);
        assertTrue(Context.userGateway.userExists(user));

        // Testing getter - not good, but I need the confidence
        assertEquals("Titface", user.getUserName());
    }
}
