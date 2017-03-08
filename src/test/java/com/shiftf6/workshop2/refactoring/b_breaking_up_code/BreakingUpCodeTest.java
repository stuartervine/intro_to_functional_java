package com.shiftf6.workshop2.refactoring.b_breaking_up_code;

import static java.lang.String.format;
import static java.util.Comparator.comparing;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BreakingUpCodeTest {
    class Item {
        private final String description;
        private final Integer price;

        Item(String description, Integer price) {
            this.description = description;
            this.price = price;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Item)) return false;
            Item item = (Item) o;
            return Objects.equals(description, item.description) &&
                    Objects.equals(price, item.price);
        }

        @Override
        public int hashCode() {
            return Objects.hash(description, price);
        }
    }

    class CheckoutMachine {
        private List<Item> items = new ArrayList<>();

        public void scan(Item item) {
            items.add(item);
        }

        public void printReceipt() {
            int total = 0;
            items.sort(comparing(o -> o.description));
            Map<Item, Integer> groupedItems = new HashMap<>();
            for (Item item : items) {
                if(!groupedItems.containsKey(item)) {
                    groupedItems.put(item, 1);
                } else {
                    groupedItems.put(item, groupedItems.get(item)+1);
                }
            }
            System.out.println("--- Receipt ---");
            for (Item item : groupedItems.keySet()) {
                int allItemPrice = groupedItems.get(item)*item.price;
                total+=allItemPrice;
                System.out.println(format("%d | %s : %dp", groupedItems.get(item), item.description, allItemPrice));
            }
            System.out.println("---------------");
            System.out.println(format("Total: %dp", total));
        }
    }

    /*
    CheckoutMachine has too many responsibilities.
    The test is a bit ugly, forcing us to set the output stream on system.
    Can we extract any sensible methods or objects to make this more testable?
     */
    @Test
    public void refactoringByBreakingUpLargeChunksOfCode() {
        CheckoutMachine checkoutMachine = new CheckoutMachine();
        checkoutMachine.scan(new Item("apple", 30));
        checkoutMachine.scan(new Item("apple", 30));
        checkoutMachine.scan(new Item("apple", 30));
        checkoutMachine.scan(new Item("orange", 45));
        checkoutMachine.scan(new Item("orange", 45));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        checkoutMachine.printReceipt();
        assertThat(out.toString(), is("--- Receipt ---\n" +
                "3 | apple : 90p\n" +
                "2 | orange : 90p\n" +
                "---------------\n" +
                "Total: 180p\n"));
    }
}
