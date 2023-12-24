/*
package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;
import static ru.netology.TestData.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        userRepository.save(USER_1);
    }

    @Test
    void shouldFindByUsername() {
        assertEquals(USER_1, userRepository.findByUsername(USERNAME_1));
    }

    @Test
    void shouldNotFindByUsername() {
        assertNull(userRepository.findByUsername(USERNAME_2));
    }
}
*/
