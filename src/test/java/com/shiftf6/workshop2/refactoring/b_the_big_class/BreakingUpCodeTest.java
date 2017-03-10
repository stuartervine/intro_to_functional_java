package com.shiftf6.workshop2.refactoring.b_the_big_class;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BreakingUpCodeTest {

    /*
    CheckoutMachine has too many responsibilities.
    The test is a bit ugly, forcing us to set the output stream on system.
    This results in a side effect when calling printReceipt.
    Can we extract any sensible methods or objects to make this more testable?
     */
    @Test
    public void refactoringByBreakingUpLargeChunksOfCode() {
        CheckoutMachine checkoutMachine = new CheckoutMachine();
        checkoutMachine.scan(new ScannableItem("apple", 30));
        checkoutMachine.scan(new ScannableItem("apple", 30));
        checkoutMachine.scan(new ScannableItem("apple", 30));
        checkoutMachine.scan(new ScannableItem("orange", 45));
        checkoutMachine.scan(new ScannableItem("orange", 45));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        checkoutMachine.printReceipt();
        assertThat(out.toString(), is("--- Receipt ---\n" +
                "3 | apple : 90p\n" +
                "2 | orange : 90p\n" +
                "---------------\n" +
                "Total: 180p\n"));
    }
}
