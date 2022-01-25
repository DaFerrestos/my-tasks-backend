package com.example.mytasksbackend.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void userNotExistsByEmail() {
        User user1 = new User("Maria", "maria@email.com", "123");
        User user2 = new User("Joao", "joao@email.com", "123");

        entityManager.persist(user1);
        entityManager.persist(user2);

        boolean userExists = userRepository.existsByEmail("pedro@email.com");

        assertFalse(userExists);
    }

    @Test
    public void userExistsByEmail() {
        User user1 = new User("Maria", "maria@email.com", "123");
        User user2 = new User("Joao", "joao@email.com", "123");
        entityManager.persist(user1);
        entityManager.persist(user2);

        boolean mariaExists = userRepository.existsByEmail("maria@email.com");
        boolean joaoExists = userRepository.existsByEmail("joao@email.com");

        assertTrue(mariaExists);
        assertTrue(joaoExists);
    }



}
