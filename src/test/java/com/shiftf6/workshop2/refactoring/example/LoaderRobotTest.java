package com.shiftf6.workshop2.refactoring.example;

import static com.shiftf6.workshop1.functionaljava.Shape.shape;
import static java.util.Arrays.asList;
import static junit.framework.TestCase.fail;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.Assert.assertThat;

import com.shiftf6.workshop1.functionaljava.Shape;
import org.junit.Test;

import java.util.List;

public class LoaderRobotTest {

    @Test
    public void canLoadSingleShapeIntoLoadingBayOfCorrectSize() throws CannotFitIntoLoadingBaysException {
        List<Shape> shapes = asList(shape(10, 10, true));
        LoaderRobot loaderRobot = new LoaderRobot(asList(100), new LoadingBayRepository());
        List<Integer> loadedBays = loaderRobot.load(shapes);
        assertThat(loadedBays, contains(0));
    }

    @Test
    public void errorsIfThereIsNotEnoughSpaceInLoadingBay() {
        List<Shape> shapes = asList(shape(11, 10, true));
        LoaderRobot loaderRobot = new LoaderRobot(asList(100), new LoadingBayRepository());
        try {
            loaderRobot.load(shapes);
            fail("Should throw cannot fit exception");
        } catch (CannotFitIntoLoadingBaysException e) {
            //good
        }
    }

    @Test
    public void trashAllEmptiesOutLoadingBays() throws CannotFitIntoLoadingBaysException {
        List<Shape> shapes = asList(shape(10, 10, true));
        LoaderRobot loaderRobot = new LoaderRobot(asList(100), new LoadingBayRepository());
        loaderRobot.load(shapes);
        List<Integer> loadedBays = loaderRobot.trashAll();
        assertThat(loadedBays, contains(100));
    }

    @Test
    public void loadsUnstackableShapesIntoIndividualLoadingBays() throws CannotFitIntoLoadingBaysException {
        List<Shape> shapes = asList(
                shape(1, 1, false),
                shape(1, 1, false)
        );
        LoaderRobot loaderRobot = new LoaderRobot(asList(100, 100), new LoadingBayRepository());
        List<Integer> loadedBays = loaderRobot.load(shapes);
        assertThat(loadedBays, contains(99, 99));
    }

    @Test
    public void loadsStackableShapesUntilLoadingBayIsFull() throws CannotFitIntoLoadingBaysException {
        List<Shape> shapes = asList(
                shape(1, 1, true),
                shape(1, 1, true),
                shape(1, 1, true)
        );
        LoaderRobot loaderRobot = new LoaderRobot(asList(2, 100), new LoadingBayRepository());
        List<Integer> loadedBays = loaderRobot.load(shapes);
        assertThat(loadedBays, contains(0, 99));
    }
}