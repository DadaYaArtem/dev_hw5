package handlers;


import objects.User;

import java.util.ArrayList;
import java.util.List;


import static Util.HttpDefaultMethods.*;
import static NonDefaultMethods.user.Get.*;
import static NonDefaultMethods.user.Post.*;
import static Util.GeneralPrefs.*;


public class UserHandler extends AbstractHandler{

    @Override
    protected  void get() {
        System.out.println("""
                To get user by username - enter username
                To log into the system - enter login
                To log out - enter logout""");
        String answer = scanner.next().trim().toLowerCase();
        switch (answer) {
            case "username" -> {
                System.out.println("Enter username");
                System.out.println(getBySmth(DEFAULT_USER_URL, scanner.next()));
            }
            case "login" -> {
                System.out.println("Enter login");
                String login = scanner.next();
                System.out.println("Enter password");
                String password = scanner.next();
                System.out.println("Status code " + logUserInSystem(login, password));
            }
            case "logout" -> System.out.println("Status code" + logUserOutSystem());
            default -> System.out.println("To get user by username - enter username\n" +
                    "To log into the system - enter login\n" +
                    "To log out - enter logout");
        }
    }

    @Override
    protected void post() {
        System.out.println("""
                To create single user - enter user
                To create users from array - enter array
                To create users from list - enter list""");
        String answer = scanner.next().trim().toLowerCase();
        switch (answer) {
            case "user":
                System.out.println("Status code " + postNewObject(DEFAULT_USER_URL, service.createUser(scanner)));
                break;
            case "array":
                System.out.println("How many users u want to create?");
                int number = scanner.nextInt();
                User[] arrayOfUsers = new User[number];
                for (int i = 0; i < arrayOfUsers.length; i++) {
                    arrayOfUsers[i] = service.createUser(scanner);
                }
                System.out.println("Status code " + createWithArrayOfUsers(arrayOfUsers));
                break;
            case "list":
                System.out.println("How many users u want to create?");
                number = scanner.nextInt();
                List<User> listOfUsers = new ArrayList<>();
                for (int i = 0; i < number; i++) {
                    listOfUsers.add(service.createUser(scanner));
                }
                System.out.println("Status code " + createWithListOfUsers(listOfUsers));
                break;
        }
    }

    @Override
    protected void put() {
        System.out.println("Enter username for update");
        String nameToUpdate = scanner.next();
        User user = (User) getBySmth(DEFAULT_USER_URL, nameToUpdate);
        User user2 = service.createUser(scanner);
        user2.setUsername(user.getUsername());
        System.out.println("Status code " + updateObject(DEFAULT_USER_URL, nameToUpdate, user));
    }

    @Override
    protected void delete() {
        System.out.println("Enter username of user you want to delete");
        System.out.println("Status code " + deleteBySmth(DEFAULT_USER_URL, scanner.next()));
    }
}
