package com.TestVerbose.helper;

import com.TestVerbose.Option;

import java.util.function.Function;

public class OptionMapSquareFunction implements Function<Integer, Integer> {

    @Override
    public Integer apply(Integer integer) {
        return integer * integer;
    }
}
