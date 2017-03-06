package com.shiftf6.workshop2.refactoring;

import com.shiftf6.workshop1.functionaljava.Shape;

import java.util.ArrayList;
import java.util.List;

public class LoaderRobot {

    private final List<Integer> loadingBays;
    private List<Integer> spaceLeftInLoadingBays;

    public LoaderRobot(List<Integer> loadingBays) {
        this.loadingBays = loadingBays;
        this.spaceLeftInLoadingBays = new ArrayList<>(loadingBays);
    }

    public List<Integer> load(List<Shape> shapes) throws CannotFitIntoLoadingBaysException {
        int totalSpaceLeft = 0;
        for (Integer spaceLeftInLoadingBay : spaceLeftInLoadingBays) {
            totalSpaceLeft = totalSpaceLeft + spaceLeftInLoadingBay;
        }

        int totalArea = 0;
        for (Shape shape : shapes) {
            totalArea = totalArea + shape.width * shape.height;
        }

        if(totalArea > totalSpaceLeft) throw new CannotFitIntoLoadingBaysException();

        ArrayList<Shape> stackableShapes = new ArrayList<>();
        //handle unstackables
        for (Shape shape : shapes) {
            if (!shape.stackable) {
                for (int i = 0; i < loadingBays.size(); i++) {
                    if (spaceLeftInLoadingBays.get(i) == loadingBays.get(i)) {
                        //empty loading bay, load it.
                        spaceLeftInLoadingBays.set(i, spaceLeftInLoadingBays.get(i) - shape.width * shape.height);
                    }
                }
            } else {
                stackableShapes.add(shape);
            }
        }

        //handle stackables
        //find first empty loading bay.
        for (int i = 0; i < loadingBays.size(); i++) {
            if (spaceLeftInLoadingBays.get(i) == loadingBays.get(i)) {
                //fill it up.
                while(!stackableShapes.isEmpty() && stackableShapes.get(0).width * stackableShapes.get(0).height <= spaceLeftInLoadingBays.get(i)) {
                    Shape shapeToLoad = stackableShapes.remove(0);
                    spaceLeftInLoadingBays.set(i, spaceLeftInLoadingBays.get(i) - (shapeToLoad.width * shapeToLoad.height));
                }
            }
        }
        return spaceLeftInLoadingBays;
    }

    public List<Integer> trashAll() {
        spaceLeftInLoadingBays = loadingBays;
        return spaceLeftInLoadingBays;
    }
}
