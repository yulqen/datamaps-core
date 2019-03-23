package com.matthewlemon.dbasiktest.fixtures;

import com.matthewlemon.dbasiktest.Context;
import com.matthewlemon.dbasiktest.TestSetup;
import com.matthewlemon.dbasiktest.entities.User;

public class DatamapMechanics {

    public DatamapMechanics() {
        TestSetup.setupContext();
    }

    public boolean addUser(String username) {
        Context.userGateway.save(new User(username));
        return true;
    }

    public void setDatamapName(String datamapName) {
    }

    public void loginUser(String username) {
    }

    public void createDatamap(String user, String name) {
    }


}
