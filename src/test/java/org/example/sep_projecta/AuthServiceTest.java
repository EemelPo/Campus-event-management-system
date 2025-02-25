package org.example.sep_projecta;

import static org.junit.jupiter.api.Assertions.*;

class AuthServiceTest {

    @org.junit.jupiter.api.Test
    void auth() {
        assertTrue(AuthService.auth("admin", "1234"));
}
}