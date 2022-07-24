package Util;

import handlers.AbstractHandler;
import handlers.PetHandler;
import handlers.StoreHandler;
import handlers.UserHandler;

import java.util.*;

public class Service {
    public static final List<String> requestList = Arrays.asList("get", "post", "put", "delete");
    public static final List<String> entities = Arrays.asList("pet", "store", "user");
    public static final List<String> statusList = Arrays.asList("available", "pending", "sold");
    public static final Map<String, AbstractHandler> commandMap = new HashMap<>();


    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(("""
                Here you can send requests to petstore.swagger.io
                """));
        commandMap.put("pet", new PetHandler());
        commandMap.put("store", new StoreHandler());
        commandMap.put("user", new UserHandler());

        while (true) {
            System.out.println(("Choose model\n" + entities + "\nTo exit enter: exit"));
            String inputEntity = scanner.nextLine().toLowerCase();
            if (inputEntity.equalsIgnoreCase("exit")) {
                System.out.println("Exiting from app");
                break;
            }
            if (!commandMap.containsKey(inputEntity)) {
                System.out.println(("Your input is incorrect"));
                continue;
            }

            System.out.println(("Choose the request\n" + requestList + "\nTo exit enter: exit"));
            String inputRequest = scanner.nextLine().toLowerCase();
            if (!requestList.contains(inputRequest)) {
                System.out.println(("Invalid request. Try again"));
            }

            commandMap.get(inputEntity).handle(inputRequest);
        }
    }
}

