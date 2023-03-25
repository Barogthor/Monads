package com.Monads.helper;

import java.util.function.Function;

public class OkMapIntegerToString implements Function<Integer, String> {
    @Override
    public String apply(Integer integer) {
        return integer.toString();
    }
}
