package com.mns.fjw;

import static com.googlecode.totallylazy.Sequences.sequence;
import static com.mns.fjw.Stacker.Shape.shape;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import com.googlecode.totallylazy.Sequence;
import org.junit.Test;

public class ReduceTest {
    /*
    Reducing is turning a collection of things into a single value.
    A typical example of a reduction operation would be summing.
     */
    @Test
    public void simpleReduceExamples() {
        Sequence<Integer> numbers = sequence(1, 2, 3);

        // traditional.
        int accumulator = 0;
        for (Integer number : numbers) {
            accumulator += number;
        }
        assertThat(accumulator, is(6));

        // functionally using a reduce method:
        assertThat(numbers.reduce((acc, i) -> acc + i), is(6));
    }

    @Test
    public void nonFunctionalToFunctionalExercise() {
        //Can we apply the reduction to our example?
        Sequence<Stacker.Shape> shapes = sequence(
                shape(1, 2, true),
                shape(10, 2, true),
                shape(5, 4, true),
                shape(3, 3, true),
                shape(1, 2, false),
                shape(1, 2, false)
        );

        Stacker stacker = new Stacker(shapes);
        assertThat(stacker.largeStackableItemsArea(), is((10*2) + (5*4)));
        assertThat(stacker.smallStackableItemsArea(), is((1*2) + (3*3)));
    }
}
