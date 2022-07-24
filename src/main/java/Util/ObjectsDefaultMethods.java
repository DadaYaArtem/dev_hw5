package Util;

import objects.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ObjectsDefaultMethods {

    private void createTags(Scanner scanner, List<Tag> tags) {
        Tag tag = new Tag();
        System.out.println("Enter pets tag id");
        tag.setId(Long.parseLong(scanner.next()));
        System.out.println("Enter pets tag name");
        tag.setName(scanner.next());
        tags.add(tag);
    }

    public Pet createPet (Scanner scanner) {
        Pet pet = new Pet();
        System.out.println("Enter pets name");
        pet.setName(scanner.next());
        System.out.println("Enter pets status");
        pet.setStatus(scanner.next());

        Category category = new Category();
        System.out.println("Enter category ID");
        category.setId(scanner.nextInt());
        System.out.println("Enter category name");
        category.setName(scanner.next());
        pet.setCategory(category);

        List<Tag> tags = new ArrayList<>();
        createTags(scanner, tags);
        System.out.println("Do you need to add an additional tag? Enter Yes or No");
        while (scanner.next().equals("yes")) {
            createTags(scanner, tags);
            System.out.println("Do you need to add an additional tag? Enter Yes or No");
        }
        pet.setTags(tags);

        List<String> urls = new ArrayList<>();
        System.out.println("Enter path to photo's URL");
        urls.add(scanner.next());
        pet.setPhotoUrls(urls);
        return pet;
    }

    public Order createOrder(Scanner scanner) {
        Order order = new Order();
        System.out.println("Enter petID");
        order.setPetId(scanner.nextLong());
        System.out.println("Enter quantity");
        order.setQuantity(scanner.nextLong());
        System.out.println("Enter ship date");
        order.setShipDate(scanner.next());
        System.out.println("Enter status");
        order.setStatus(scanner.next());
        return order;
    }

    public User createUser(Scanner scanner) {
        User user = new User();
        System.out.println("Enter user's ID");
        user.setId(scanner.nextLong());
        System.out.println("Enter user's username");
        user.setUsername(scanner.next());
        System.out.println("Enter user's first name");
        user.setFirstName(scanner.next());
        System.out.println("Enter user's lastname");
        user.setLastName(scanner.next());
        System.out.println("Enter user's email");
        user.setEmail(scanner.next());
        System.out.println("Enter user's password");
        user.setPassword(scanner.next());
        System.out.println("Enter user's phone");
        user.setPhone(scanner.next());
        System.out.println("Enter user's status (Long)");
        user.setUserStatus(scanner.nextLong());
        return user;
    }

    public User createDefaultUser(Scanner scanner) {
        User user = new User();
        user.setId(1);
        user.setUsername("name");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setEmail("@email");
        user.setPassword("password");
        user.setPhone("+380");
        user.setUserStatus(0);
        return user;
    }
}
