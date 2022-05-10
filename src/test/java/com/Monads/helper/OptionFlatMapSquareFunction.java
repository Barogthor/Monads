package com.Monads.helper;

import com.Monads.Option;

import java.util.function.Function;

public class OptionFlatMapSquareFunction implements Function<Integer, Option<Integer>> {

    @Override
    public Option<Integer> apply(Integer integer) {
        return Option.of(integer * integer);
    }
}
