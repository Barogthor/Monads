package com.Monads.helper;

import java.util.function.Function;

public class ErrorMapSquareFunction implements Function<Double, Double> {

    @Override
    public Double apply(Double number) {
        return number * number;
    }
}
