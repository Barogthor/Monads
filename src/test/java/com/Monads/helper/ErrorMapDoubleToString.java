package com.Monads.helper;

import java.util.function.Function;

public class ErrorMapDoubleToString implements Function<Double, String> {
    @Override
    public String apply(Double aDouble) {
        return aDouble.toString();
    }
}
