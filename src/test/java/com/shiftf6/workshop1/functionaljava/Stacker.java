package com.shiftf6.workshop1.functionaljava;

import java.util.List;

public class Stacker {

    public int totalAreaOfShapes(List<Shape> shapes) {
        int area = 0;
        for (Shape shape : shapes) {
            area += shape.width * shape.height;
        }
        return area;
    }

    public int stackableItemsArea(List<Shape> shapes) {
        int area = 0;
        for (Shape shape : shapes) {
            if (shape.stackable) {
                area += shape.width * shape.height;
            }
        }
        return area;
    }

    public int unstackableItemsArea(List<Shape> shapes) {
        int area = 0;
        for (Shape shape : shapes) {
            if (!shape.stackable) {
                area += shape.width * shape.height;
            }
        }
        return area;
    }

}
