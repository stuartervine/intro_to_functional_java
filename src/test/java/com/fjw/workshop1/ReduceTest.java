package com.fjw.workshop1;

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
        List<Stacker.Shape> shapes = asList(
                Stacker.Shape.shape(1, 2, true),
                Stacker.Shape.shape(10, 2, true),
                Stacker.Shape.shape(5, 4, true),
                Stacker.Shape.shape(3, 3, true),
                Stacker.Shape.shape(1, 2, false),
                Stacker.Shape.shape(1, 2, false)
        );

        Stacker stacker = new Stacker(shapes);
        assertThat(stacker.largeStackableItemsArea(), is((10 * 2) + (5 * 4)));
        assertThat(stacker.smallStackableItemsArea(), is((1 * 2) + (3 * 3)));
    }
}
