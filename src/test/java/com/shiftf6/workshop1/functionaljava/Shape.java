package com.shiftf6.workshop1.functionaljava;

public class Shape {
    public final int width;
    public final int height;
    public final boolean stackable;

    public static Shape shape(int width, int height, boolean stackable) {
        return new Shape(width, height, stackable);
    }

    public Shape(int width, int height, boolean stackable) {
        this.width = width;
        this.height = height;
        this.stackable = stackable;
    }

    public int area() {
        return width * height;
    }
}
