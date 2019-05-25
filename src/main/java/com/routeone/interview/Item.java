package com.routeone.interview;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.Comparator;
import java.util.List;

public class Item {
    private int priceIndex;
    private double price;

    private List<String> information;

    public Item(List<String> information) {
        this.information = information;
        this.priceIndex = 0;
        this.price = value(information);
    }

    public static Comparator<Item> ItemComparator = new Comparator<Item>() {
        @Override
        public int compare(Item o1, Item o2) {
            double ItemPrice1 = o1.getPrice();
            double ItemPrice2 = o2.getPrice();

            int comp = Double.compare(ItemPrice2, ItemPrice1);

            if (comp != 0) {
                return comp;
            }

            String Item1;
            String Item2;
            if (o1.priceIndex != 0) {
                Item1 = o1.information.get(0);
                Item2 = o2.information.get(0);
            } else {
                Item1 = o1.information.get(1);
                Item2 = o2.information.get(1);
            }

            return Item1.compareTo(Item2);
        }
    };

    private double value(List<String> information) {
        double num = 0;
        for (int i = 0; i < information.size(); i++) {
            if (NumberUtils.isCreatable(information.get(i))) {
                num = Double.parseDouble(information.get(i));
                priceIndex = i;
            }
        }
        return num;
    }

    public int getPriceIndex() {
        return priceIndex;
    }

    public void setPriceIndex(int priceIndex) {
        this.priceIndex = priceIndex;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<String> getInformation() {
        return information;
    }

    public void setInformation(List<String> information) {
        this.information = information;
    }
}
