package handlers;

import static Util.HttpDefaultMethods.*;
import static Util.GeneralPrefs.ORDER;

import static NonDefaultMethods.store.Get.*;
import static Util.GeneralPrefs.*;

public class StoreHandler extends AbstractHandler {
    @Override
    protected void get() {
        System.out.println("To get order - enter order\n" + "To get inventory - enter inventory");
        String answer = scanner.next();
        switch (answer) {
            case "order" -> {
                System.out.println(("Enter order id"));
                String id = String.valueOf(scanner.nextLong());
                System.out.println(getBySmth(DEFAULT_ORDER_URL + ORDER, id));
            }
            case "inventory" -> System.out.println(findOrderStatuses());
            default -> System.out.println("Enter order or inventory");
        }
    }

    @Override
    protected void post() {
        System.out.println("Status code " + postNewObject(DEFAULT_ORDER_URL + ORDER, service.createOrder(scanner)));
    }

    @Override
    protected void put() {
        System.out.println("Not implemented");
    }

    @Override
    protected void delete() {
        System.out.println("Enter id of order you want to delete");
        System.out.println("Status code " + deleteBySmth(DEFAULT_ORDER_URL + ORDER , String.valueOf(scanner.nextLong())));
    }
}
