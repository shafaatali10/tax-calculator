package com.shafaat.seisma.taxcalc.rules;

public interface ITaxRule<I, O> {
    boolean doesFallInRange(I input);
    O process(I input);
}
