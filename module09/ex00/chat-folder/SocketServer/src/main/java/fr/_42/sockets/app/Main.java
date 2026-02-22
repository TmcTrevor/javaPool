package fr._42.sockets.app;

import fr._42.sockets.config.SocketsApplicationConfig;
import fr._42.sockets.models.User;
import fr._42.sockets.server.Server;
import fr._42.sockets.services.UsersService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class Main {

    private static int getPort(String[] args) throws Exception
    {
        if (args.length != 1)
            throw new Exception("Arguments number is wrong");
        if (!args[0].contains("="))
            throw  new Exception("First argument with wrong syntax");
        return Integer.parseInt(args[0].split("=")[1]);
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
        try {
            int port = getPort(args);

            UsersService usersService = context.getBean(UsersService.class);
            Server  server = new Server(port, usersService);
            server.startProcess();
//            String password = usersService.signUp(email);
//            System.out.println("password generated" + password);
//            Optional<User> optionalUser = usersService.getUserByUsername(email);
//            optionalUser.ifPresent(user -> System.out.println("user out " + user));
//            optionalUser.ifPresent(user -> System.out.println("is equal" + password.equals(user.getPassword())));
        } catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
    }
}