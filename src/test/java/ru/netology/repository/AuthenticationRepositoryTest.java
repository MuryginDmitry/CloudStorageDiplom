package ru.netology.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static ru.netology.TestData.*;

class AuthenticationRepositoryTest {

    private AuthenticationRepository authenticationRepository;

    private final Map<String, String> testTokensAndUsernames = new ConcurrentHashMap<>();

    @BeforeEach
    void setUp() {
        authenticationRepository = new AuthenticationRepository();
        authenticationRepository.putTokenAndUsername(TOKEN_1, USERNAME_1);
        testTokensAndUsernames.clear();
        testTokensAndUsernames.put(TOKEN_1, USERNAME_1);
    }

    @Test
    void shouldAddTokenAndUsername() {
        assertNull(authenticationRepository.getUsernameByToken(TOKEN_2));
        authenticationRepository.putTokenAndUsername(TOKEN_2, USERNAME_2);
        assertEquals(USERNAME_2, authenticationRepository.getUsernameByToken(TOKEN_2));
    }

    @Test
    void shouldRemoveTokenAndUsernameByToken() {
        assertNotNull(authenticationRepository.getUsernameByToken(TOKEN_1));
        authenticationRepository.removeTokenAndUsernameByToken(TOKEN_1);
        assertNull(authenticationRepository.getUsernameByToken(TOKEN_1));
    }

    @Test
    void shouldRetrieveUsernameByToken() {
        assertEquals(testTokensAndUsernames.get(TOKEN_1), authenticationRepository.getUsernameByToken(TOKEN_1));
    }

    @AfterEach
    void tearDown() {
    }
}
