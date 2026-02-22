package _42.spring.service.services;

import _42.spring.service.models.User;
import _42.spring.service.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;


@Component
public class UsersServiceImpl implements UsersService {


    private UsersRepository usersRepository;

    private String generatePassword() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    @Autowired
    public UsersServiceImpl(@Qualifier("usersRepositoryJdbcImpl") UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public String signUp(String email)
    {
        String password = generatePassword();
        User user = new User(email, password);
        System.out.println("user in serv pre" + user);
        usersRepository.save(user);
        return password;
    }

    public Optional<User> getUserByEmail(String email)
    {
        return usersRepository.findByEmail(email);
    }


}
