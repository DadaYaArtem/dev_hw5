package handlers;

import Util.HttpDefaultMethods;
import Util.Service;

import java.util.*;

import static Util.HttpDefaultMethods.*;
import static NonDefaultMethods.pet.Get.*;
import static NonDefaultMethods.pet.Post.*;

import static Util.GeneralPrefs.*;

public class PetHandler extends AbstractHandler{
    @Override
    protected void get() {
        System.out.println(("Get by ID or status?"));
        String answer = scanner.nextLine().toLowerCase().trim();
        switch (answer) {
            case "id" -> {
                System.out.println(("Enter pet id"));
                String id = String.valueOf(scanner.nextLong());
                System.out.println(getBySmth(DEFAULT_PET_URL, id));
            }
            case "status" -> {
                System.out.println(("Available statuses: " + Service.statusList));
                String statusesAsString = scanner.nextLine();
                List<String> statuses = new ArrayList<>();
                if (statusesAsString.contains(",")) {
                    statuses = List.of(statusesAsString.replaceAll("\\s+", "").split(","));
                } else {
                    statuses.add(statusesAsString);
                }
                System.out.println(findPetByStatus(statuses));
            }
            default -> System.out.println("Wrong input");
        }
    }


    @Override
    protected void post() {
        System.out.println("Create new pet - enter new\n"
        + "upload an image by path - enter image\n"
        + "update existing pet - enter update");

        String input = scanner.nextLine().trim().toLowerCase();
        switch (input) {
            case "new":
                System.out.println(postNewObject(DEFAULT_PET_URL, service.createPet(scanner)));
                break;
            case "image":
                System.out.println("Enter pet's ID");
                long id = Long.parseLong(scanner.next());
                String temp = scanner.nextLine();
                System.out.println("Enter url");
                String url = scanner.nextLine();
                System.out.println("Enter metadata if needed");
                String data = scanner.nextLine();
                System.out.println("Status code: " + addPhotoToPet(id, url, data));
                break;
            case "update":
                System.out.println("Enter pet's ID");
                id = Long.parseLong(scanner.next());
                temp = scanner.nextLine();
                System.out.println("Enter new name");
                String name = scanner.nextLine();
                System.out.println("Enter new status");
                String status = scanner.nextLine();
                System.out.println("Status code " + updatePetWithFormData(id, name, status));
                break;
            default:
                System.out.println("Enter correct option");
        }
    }

    @Override
    protected void put() {
        System.out.println("Status code " + updateObject(DEFAULT_PET_URL,"", service.createPet(scanner)));
    }

    @Override
    protected void delete() {
        System.out.println("Enter id of pet you want to delete");
        System.out.println("Status code " + HttpDefaultMethods.deleteBySmth(DEFAULT_PET_URL, String.valueOf(scanner.nextLong())));
    }



}
