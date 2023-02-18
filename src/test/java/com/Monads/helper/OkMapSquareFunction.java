package com.Monads.helper;

import com.Monads.Result;

import java.util.function.Function;

public class OkMapSquareFunction<E> implements Function<Integer, Result<E, Integer>> {

    @Override
    public Result<E, Integer> apply(Integer integer) {
        return Result.Ok(integer * integer);
    }
}
