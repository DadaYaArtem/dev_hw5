package handlers;



import Util.ObjectsDefaultMethods;



import java.util.Scanner;

public abstract class AbstractHandler {
    ObjectsDefaultMethods service = new ObjectsDefaultMethods();
    Scanner scanner = new Scanner(System.in);

    public void handle(String params) {

        switch (params.toLowerCase().trim()) {
            case "get" -> get();
            case "post" -> post();
            case "put" -> put();
            case "delete" -> delete();
        }
    }

    protected abstract void get();

    protected abstract void post();

    protected abstract void put();

    protected abstract void delete();
}
