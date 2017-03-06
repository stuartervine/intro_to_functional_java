package com.shiftf6.workshop1.functionaljava;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import java.util.List;

public class ReduceTest {
    /*
    Reducing is turning a collection of things into a single value.
    A typical example of a reduction operation would be summing.
     */
    @Test
    public void simpleReduceExamples() {
        List<Integer> numbers = asList(1, 2, 3);

        // traditional.
        int accumulator = 0;
        for (Integer number : numbers) {
            accumulator += number;
        }
        assertThat(accumulator, is(6));

        // functionally using a reduce method:
        assertThat(
                numbers.stream().reduce((acc, i) -> acc + i),
                is(6)
        );
    }

    @Test
    public void nonFunctionalToFunctionalExercise() {
        //Can we apply the reduction to our example?
        List<Shape> shapes = asList(
                Shape.shape(1, 2, true),
                Shape.shape(10, 2, true),
                Shape.shape(5, 4, true),
                Shape.shape(3, 3, true),
                Shape.shape(1, 2, false),
                Shape.shape(1, 2, false)
        );

        Stacker stacker = new Stacker();
        assertThat(stacker.totalAreaOfShapes(shapes), is(55));
        assertThat(stacker.stackableItemsArea(shapes), is((1*2) + (10*2) + (5*4) + (3*3)));
        assertThat(stacker.unstackableItemsArea(shapes), is((1*2) + (1*2) ));
    }
}
