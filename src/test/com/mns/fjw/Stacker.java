package com.mns.fjw;

import com.googlecode.totallylazy.Sequence;

public class Stacker {
    private final Sequence<Shape> shapes;

    public Stacker(Sequence<Shape> shapes) {
        this.shapes = shapes;
    }

    public int largeStackableItemsArea() {
        int area = 0;
        for(Stacker.Shape shape : shapes) {
            if(shape.width * shape.height > 15) {
                if(shape.stackable) {
                    area += shape.width*shape.height;
                }
            }
        }
        return area;
    }

    public int smallStackableItemsArea() {
        int area = 0;
        for(Stacker.Shape shape : shapes) {
            if(shape.width * shape.height <= 15) {
                if(shape.stackable) {
                    area += shape.width*shape.height;
                }
            }
        }
        return area;
    }

    public static class Shape {
        public final int width;
        public final int height;
        public final boolean stackable;

        public static Shape shape(int width, int height, boolean stackable) {
            return new Shape(width,height,stackable);
        }

        public Shape(int width, int height, boolean stackable) {
            this.width = width;
            this.height = height;
            this.stackable = stackable;
        }
    }
}
