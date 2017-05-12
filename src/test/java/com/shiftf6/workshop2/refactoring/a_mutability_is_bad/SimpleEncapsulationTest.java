package com.shiftf6.workshop2.refactoring.a_mutability_is_bad;

import static java.lang.String.format;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SimpleEncapsulationTest {
    class Item {
        private String description;
        private Integer price = 0;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return format("%s @ %d", description, price);
        }
    }

    private void checkForBadItems(Item item) {
        item.setPrice(6);
        item.setDescription("I can do whatever I want to your object behind your back");
    }

    class Basket {
        List<Item> items = new ArrayList<>();
    }

    class BasketTotaller {
        public Integer totalPrice(Basket basket) {
            int total = 0;
            for (Item item : basket.items) {
                total += item.price;
            }
            return total;
        }
    }

    /*
    The above is a fairly typical java bean. What's wrong with it?
    We've got mutability, which is bad. We can screw up the creation of an item.
    Can we remove the mutability?
    */
    //Fix me!
    @Test
    public void whyIsMutabilityBad() {
        Item apple = new Item();
        apple.setDescription("Apple");

        assertThat(apple.toString(), is("Apple @ 12"));
    }

    @Test
    public void whyMutabilityIsAwful() {
        Item apple = new Item();
        apple.setDescription("Apple");
        apple.setPrice(12);

        checkForBadItems(apple);
        assertThat(apple.toString(), is("Apple @ 12"));
    }

    /*
    There a number of issues in the below code.
    The basket is very anaemic and it exposes it's privates.
    The basket totaller, may not actually be a basket totaller.
    Assume we've fixed the item & removed the duplicate method calls.
     */
    @Test
    public void anaemicObjectsBreakEncapsulation() {
        Item apple = new Item();
        apple.setDescription("Apple");
        apple.setPrice(12);

        Item orange = new Item();
        orange.setDescription("Orange");
        orange.setPrice(30);

        Basket basket = new Basket();
        basket.items.add(apple);
        basket.items.add(orange);

        assertThat(new BasketTotaller().totalPrice(basket), is(42));
    }
}
