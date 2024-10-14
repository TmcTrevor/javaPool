package fr._42.services;

import fr._42.exceptions.EntityNotFoundException;
import fr._42.repositories.UsersRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

import fr._42.models.User;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class UsersServiceImplTest {


    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private UsersServiceImpl usersService;

    @BeforeEach
    void setUp() {
//        MockitoAnnotations.initMocks(this);

        MockitoAnnotations.openMocks(this);
    }


    @Test
    void UserService_Authenticate_CorrectLoginAndPasswordReturnTrue(

    ) {
        User user = new User(1, "testUser", "correctPassword", false);


        when(usersRepository.findByLogin("testUser")).thenReturn(user);

        assertTrue(usersService.authenticate("testUser", "correctPassword"));
        verify(usersRepository).update(user);
    }

    @Test
    void UserService_Authenticate_IncorrectLoginThrowException()
    {
        User user = new User(1, "testUser2", "Password", false);

        when(usersRepository.findByLogin("testUser2")).thenThrow(new EntityNotFoundException("User with login testUser2 not found\""));
        assertThrows(EntityNotFoundException.class, () -> usersService.authenticate("testUser2", "Password"));
    }


    @Test
    void UserService_Authenticate_IncorrectPasswordReturnsFalse(){
        User user = new User(1, "testUser", "correctPassword", false);
        when(usersRepository.findByLogin("testUser")).thenReturn(user);

        assertFalse(usersService.authenticate("testUser", "wrongPassword"));
    }
}
