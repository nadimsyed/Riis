package com.routeone.interview;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.*;

public class StoreRegister {

    private int count;
    private int categories;
    private List<String> file;
    private List<Item> objects;
    private List<Item> orderedItems;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCategories() {
        return categories;
    }

    public void setCategories(int categories) {
        this.categories = categories;
    }

    public List<String> getFile() {
        return file;
    }

    public void setFile(List<String> file) {
        this.file = file;
    }

    public List<Item> getObjects() {
        return objects;
    }

    public void setObjects(List<Item> objects) {
        this.objects = objects;
    }

    public void loadInventory(File inventoryFile) throws FileNotFoundException {

        Scanner scanner = new Scanner(new FileReader(inventoryFile));
        List<String> line = new ArrayList<String>();
        List<String> file = new ArrayList<String>();
        while (scanner.hasNext()) {
            line = parseLine(scanner.nextLine());
            for (int i = 0; i < line.size(); i++) {
                file.add(line.get(i));
            }
            count++;
            categories = line.size();
        }

        objects = new ArrayList<Item>();
        List<String> info = new ArrayList<String>();
        List<String> allInfo = new ArrayList<String>();
        int j = 0;
        for (int y = 0; y < count * categories; y += categories) {
            info = (file.subList(y, y + categories));
            Item item = new Item(info);
            objects.add(item);
            item = null;
        }

        this.file = file;
        scanner.close();
    }

    public static List<String> parseLine(String line) {
        List<String> result = new ArrayList<String>();
        String splitter = ",";

        //if empty, return
        if (line == null && line.isEmpty())
            return result;

        String[] values = line.split(splitter);
        result = Arrays.asList(values);

        return result;
    }

    public Receipt checkoutOrder(List<String> items) {

        final List<Integer> order = new ArrayList<Integer>();
        List<String> potential = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            potential.add(i + "");
        }

        for (int i = 0; i < items.size(); i++) {
            if (potential.contains(items.get(i))) {
                order.add(Integer.parseInt(items.get(i)));
            } else {
                System.out.println("Invalid input for items in our inventory. Please try another time!");
                break;
            }
        }

        Receipt receipt = new Receipt() {
            @Override
            public String getFormattedTotal() {
                DecimalFormat decimalFormat = new DecimalFormat("$#,###,###,###.##");
                double total = 0;
                for (int i = 0; i < order.size(); i++) {
                    total += objects.get(order.get(i)).getPrice();
                }
                return decimalFormat.format(total);
            }

            @Override
            public List<String> getOrderedItems() {
                orderedItems = new ArrayList<Item>();
                List<String> sorted = new ArrayList<String>();
                for (int i = 0; i < order.size(); i++) {
                    orderedItems.add(objects.get(order.get(i)));
                }
                Collections.sort(orderedItems, Item.ItemComparator);
                for (int i = 0; i < orderedItems.size(); i++) {
                    sorted.add(orderedItems.get(i).getInformation().toString());
                }
                return sorted;
            }
        };
        return receipt;
    }
}