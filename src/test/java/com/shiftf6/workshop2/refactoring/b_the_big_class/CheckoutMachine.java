package com.shiftf6.workshop2.refactoring.b_the_big_class;

import static java.lang.String.format;
import static java.util.Comparator.comparing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckoutMachine {
    private List<ScannableItem> items = new ArrayList<>();

    public void scan(ScannableItem item) {
        items.add(item);
    }

    public void printReceipt() {
        int total = 0;
        items.sort(comparing(o -> o.description));
        Map<ScannableItem, Integer> groupedItems = new HashMap<>();
        for (ScannableItem item : items) {
            if(!groupedItems.containsKey(item)) {
                groupedItems.put(item, 1);
            } else {
                groupedItems.put(item, groupedItems.get(item)+1);
            }
        }
        System.out.println("--- Receipt ---");
        for (ScannableItem item : groupedItems.keySet()) {
            int allItemPrice = groupedItems.get(item)*item.price;
            total+=allItemPrice;
            System.out.println(format("%d | %s : %dp", groupedItems.get(item), item.description, allItemPrice));
        }
        System.out.println("---------------");
        System.out.println(format("Total: %dp", total));
    }
}
