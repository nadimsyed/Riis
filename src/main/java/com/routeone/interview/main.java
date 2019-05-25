package com.routeone.interview;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        StoreRegister storeRegister = new StoreRegister();
        storeRegister.loadInventory(new File("RouteOneSampleInventory.csv"));

        int count = storeRegister.getCount();
        int categories = storeRegister.getCategories();
        List<String> file = storeRegister.getFile();
        List<String> info = new ArrayList<String>();
        List<String> items = new ArrayList<String>();
        boolean truth = true;
        boolean returnReceipt = true;

        System.out.println("Below will be the list of items currently held in our inventory.");
        System.out.println("Please select the corresponding item number for items you desire.");

        for (int y = 0; y < count * categories; y += categories) {
            info = (file.subList(y, y + categories));
            System.out.println("Item " + ((y / 3) + 1) + ": " + info);
        }

        Scanner scanner = new Scanner(System.in);
        while (truth) {
            String cont;
            System.out.println("Which item do you want to purchase?");
            try {
                items.add(Integer.toString((Integer.parseInt(scanner.next())) - 1));
            } catch (Exception e) {
                System.out.println("Invalid input for items in our inventory. Please try another time!");
                returnReceipt = false;
                break;
            }
            System.out.println("Do you want to continue to add items? (Y/N)");
            cont = scanner.next();
            if (cont.toLowerCase().equals("y")) {
                truth = true;
            } else if (cont.toLowerCase().equals("n")) {
                truth = false;
                System.out.println("Thank you for selecting your desired items.");
            } else {
                System.out.println("Invalid option will assume you are done selecting items.");
                truth = false;
            }
        }

        if (returnReceipt) {
            Receipt receipt = storeRegister.checkoutOrder(items);
            System.out.println("Formatted Total: " + receipt.getFormattedTotal());
            System.out.println("Receipt sorted: " + receipt.getOrderedItems());
        }
    }
}