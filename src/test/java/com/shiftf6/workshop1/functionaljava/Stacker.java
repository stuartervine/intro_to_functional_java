package com.shiftf6.workshop1.functionaljava;

import java.util.ArrayList;
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
        List<Shape> stackableShapes = new ArrayList<>();
        for (Shape shape : shapes) {
            if (shape.stackable) {
                if (shape.width * shape.height <= 15) {
                    stackableShapes.add(shape);
                }
            }
        }
        for (Shape shape : stackableShapes) {
            area += shape.width * shape.height;
        }
        return area;
    }

    public int unstackableItemsArea(List<Shape> shapes) {
        int area = 0;
        List<Shape> unstackableShapes = new ArrayList<>();
        for (Shape shape : shapes) {
            if (!shape.stackable || shape.width * shape.height > 15) {
                unstackableShapes.add(shape);
            }
        }
        for (Shape shape : unstackableShapes) {
            area += shape.width * shape.height;
        }
        return area;
    }
}
