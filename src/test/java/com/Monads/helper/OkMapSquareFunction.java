package com.Monads.helper;

import java.util.function.Function;

public class OkMapSquareFunction implements Function<Integer, Integer> {

    @Override
    public Integer apply(Integer number) {
        return number * number;
    }
}
