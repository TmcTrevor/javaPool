package _42.spring.service.services;


import _42.spring.service.config.TestApplicationConfig;
import _42.spring.service.models.User;
import _42.spring.service.repositories.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestApplicationConfig.class)
public class UsersServiceImplTest {


    @Autowired
    private UsersService usersService;

    @Autowired
    @Qualifier("usersRepositoryJdbcImpl")
    private UsersRepository usersRepository; // to clean DB between tests

    @BeforeEach
    public void setUp() {
        usersRepository.cleanAll(); // fresh state before each test
    }


    @Test
    public void signUp_shouldReturnTemporaryPassword()
    {
        String password = usersService.signUp("alice@gmail.com");

        assertNotNull(password);
        assertFalse(password.isEmpty());
        assertEquals(8, password.length());
    }

    @Test
    public void signUp_shouldSaveUserInDatabase() {
        usersService.signUp("alice@gmail.com");

        assertNotNull(usersService.getUserByEmail("alice@gmail.com"));
        assertTrue(usersService.getUserByEmail("alice@gmail.com").isPresent());
    }


    @Test
    public void signUp_shouldSavePasswordInDatabase() {
        String password = usersService.signUp("charlie@gmail.com");

        User saved = usersRepository.findByEmail("charlie@gmail.com")
                .orElseThrow(() -> new RuntimeException("User not found"));

        assertEquals(password, saved.getPassword()); // returned password matches saved one
    }


    }
