package com.shiftf6.workshop2.refactoring.b_breaking_up_code;

import java.util.Objects;

public class ScannableItem {
    public final String description;
    public final Integer price;

    ScannableItem(String description, Integer price) {
        this.description = description;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScannableItem)) return false;
        ScannableItem item = (ScannableItem) o;
        return Objects.equals(description, item.description) &&
                Objects.equals(price, item.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, price);
    }
}
