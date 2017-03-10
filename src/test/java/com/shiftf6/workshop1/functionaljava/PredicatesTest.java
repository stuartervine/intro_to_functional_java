package com.shiftf6.workshop1.functionaljava;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import java.util.List;
import java.util.function.Predicate;

public class PredicatesTest {

    @Test
    public void simplePredicateExamples() {

        /*
        When things are true.
        Note: Predicates have a 'test' method that evaluates them against the provided argument
         */
        Predicate<Object> alwaysTrue = x -> true;
        assertThat(alwaysTrue.test("bob"), is(true));

        /*
        When things are false.
         */
        Predicate<Object> alwaysFalse = x -> false;
        assertThat(alwaysFalse.test("bob"), is(false));

        /*
        How to apply these to a collection?
         */
        List<String> names = asList("bob", "fred", "john");
        assertThat(
                names.stream().filter(alwaysTrue).collect(toList()),
                contains("bob", "fred", "john")
        );
        assertThat(
                names.stream().filter(alwaysFalse).collect(toList()),
                emptyIterable()
        );
    }

    @Test
    public void predicates_exercise1() {
        List<String> names = asList("bob", "fred", "john doe");

        /*
        Great, how about doing something meaningful with a predicate?
        Can you make a predicate to filter strings with 4 or more characters?
         */
        Predicate<String> lengthIsFourOrMore = string -> false;
        assertThat(
                names.stream().filter(lengthIsFourOrMore).collect(toList()),
                contains("fred", "john doe")
        );
    }

    @Test
    public void predicates_exercise2() {
        /*
        Sweet... how about doing something with an object, can we filter those using a predicate too?
        We'll use sequences going forward, but you can replace them with stream().filter(...).collects on
        Lists if you want.
        Imagine we have a class: Person
         */
        class Person {
            public final String name;
            public final String livesInCity;
            public final String smokes;

            public Person(String name, String livesInCity, String smokes) {
                this.name = name;
                this.livesInCity = livesInCity;
                this.smokes = smokes;
            }

            public String toString() { return "Person: " + name; }
        }

        // and we have a collection of these...
        Person bob = new Person("Bob", "London", "Y");
        Person john = new Person("John", "Aberdeen", "Y");
        Person wilma = new Person("Wilma", "London", "N");
        Person jackie = new Person("Jackie", "Southampton", "N");
        List<Person> people = asList(
                bob,
                john,
                wilma,
                jackie
        );

        // write a predicate to find all smokers.
        Predicate<Person> males = p -> false;
        assertThat(
                people.stream().filter(males).collect(toList()),
                contains(bob, john)
        );

        // write a predicate to find people living in London.
        Predicate<Person> livingInLondon = p -> false;
        assertThat(
                people.stream().filter(livingInLondon).collect(toList()),
                contains(bob, wilma)
        );

        // any idea how to find smokers living in london?
        assertThat(
                people.stream().filter(p -> true).collect(toList()),
                contains(bob)
        );

        // how about non-smokers living outside of london?
        assertThat(
                people.stream().filter(p -> true).collect(toList()),
                contains(jackie)
        );
    }

    @Test
    public void nonFunctionalToFunctionalExercise() {
        List<Shape> shapes = asList(
                Shape.shape(1, 2, true),
                Shape.shape(10, 2, true),
                Shape.shape(5, 4, true),
                Shape.shape(3, 3, true),
                Shape.shape(1, 2, false),
                Shape.shape(1, 2, false)
        );
        /*
        Can you convert the internals of stacker to use predicates?
         */
        Stacker stacker = new Stacker();
        assertThat(stacker.totalAreaOfShapes(shapes), is(55));
        assertThat(stacker.stackableItemsArea(shapes), is((1*2) + (3*3)));
        assertThat(stacker.unstackableItemsArea(shapes), is((1*2) + (10*2) + (5*4) + (1*2)));
    }

}