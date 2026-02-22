package _42.spring.service.application;

import _42.spring.service.config.ApplicationConfig;
import _42.spring.service.models.User;
import _42.spring.service.repositories.UsersRepository;
import _42.spring.service.services.UsersService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        UsersService usersService = context.getBean(UsersService.class);
        String email = "test1425543@gmail.com";
        String password = usersService.signUp(email);
        System.out.println("password generated" + password);
        Optional<User> optionalUser = usersService.getUserByEmail(email);
        optionalUser.ifPresent( user -> System.out.println("user out " + user));
        optionalUser.ifPresent(user -> System.out.println("is equal" + password.equals(user.getPassword())));

    }
}