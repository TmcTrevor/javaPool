package fr._42.sockets.services;

import fr._42.sockets.models.User;
import fr._42.sockets.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;


@Component
public class UsersServiceImpl implements UsersService {


    private UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder;

    private String generatePassword() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    @Autowired
    public UsersServiceImpl(@Qualifier("usersRepositoryImpl") UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void signUp(String username, String password)
    {
        String hashedPassword = passwordEncoder.encode(password);
        User user = new User(username, hashedPassword);
        System.out.println("user in serv pre" + user);
        usersRepository.save(user);
    }

    public Optional<User> getUserByUsername(String username)
    {
        return usersRepository.findByUsername(username);
    }


}
