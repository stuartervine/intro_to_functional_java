package com.shiftf6.workshop2.refactoring;

import com.shiftf6.workshop1.functionaljava.Shape;

import java.util.ArrayList;
import java.util.List;

public class LoaderRobot {

    private final LoadingBayRepository loadingBayRepository;

    public LoaderRobot(List<Integer> loadingBays, LoadingBayRepository loadingBayRepository) {
        this.loadingBayRepository = loadingBayRepository;
        for (Integer loadingBay : loadingBays) {
            loadingBayRepository.addLoadingBay(loadingBay);
        }
        loadingBayRepository.afterBaysSetInitialize();
    }

    public List<Integer> load(List<Shape> shapes) throws CannotFitIntoLoadingBaysException {
        int totalSpaceLeft = totalSpaceLeftInLoadingBays();
        int totalArea = totalAreaOfShapesToLoad(shapes);
        if(totalArea > totalSpaceLeft) throw new CannotFitIntoLoadingBaysException();

        ArrayList<Shape> stackableShapes = processUnstackables(shapes);
        processStackables(stackableShapes);
        return loadingBayRepository.allBays();
    }

    private void processStackables(ArrayList<Shape> stackableShapes) {
        List<Integer> allBays = loadingBayRepository.allBays();
        for (int i = 0; i < allBays.size(); i++) {
            if (loadingBayRepository.isEmpty(i)) {
                //fill it up.
                while(!stackableShapes.isEmpty() && stackableShapes.get(0).width * stackableShapes.get(0).height <= allBays.get(i)) {
                    Shape shapeToLoad = stackableShapes.remove(0);
                    loadingBayRepository.loadBay(i, allBays.get(i) - (shapeToLoad.width * shapeToLoad.height));
                }
            }
        }
    }

    private ArrayList<Shape> processUnstackables(List<Shape> shapes) {
        ArrayList<Shape> stackableShapes = new ArrayList<>();
        //handle unstackables
        for (Shape shape : shapes) {
            if (!shape.stackable) {
                List<Integer> allBays = loadingBayRepository.allBays();
                for (int i = 0; i < allBays.size(); i++) {
                    if (loadingBayRepository.isEmpty(i)) {
                        //empty loading bay, load it.
                        loadingBayRepository.loadBay(i, allBays.get(i) - shape.width * shape.height);
                    }
                }
            } else {
                stackableShapes.add(shape);
            }
        }
        return stackableShapes;
    }

    private int totalAreaOfShapesToLoad(List<Shape> shapes) {
        int totalArea = 0;
        for (Shape shape : shapes) {
            totalArea = totalArea + shape.width * shape.height;
        }
        return totalArea;
    }

    private int totalSpaceLeftInLoadingBays() {
        int totalSpaceLeft = 0;
        for (Integer spaceLeftInLoadingBay : loadingBayRepository.allBays()) {
            totalSpaceLeft = totalSpaceLeft + spaceLeftInLoadingBay;
        }
        return totalSpaceLeft;
    }

    public List<Integer> trashAll() {
        loadingBayRepository.deleteAllTemporaryLoadingBays();
        loadingBayRepository.resetTempLoadingBaysFromOriginals();
        return loadingBayRepository.allBays();
    }
}
