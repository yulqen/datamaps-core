package com.matthewlemon.dbasiktest.gateways;

import com.matthewlemon.dbasiktest.entities.User;

public interface UserGateway {
    User save(User user);
}
