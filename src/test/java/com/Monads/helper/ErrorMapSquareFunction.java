package com.Monads.helper;

import com.Monads.Result;

import java.util.function.Function;

public class ErrorMapSquareFunction<T> implements Function<Integer, Result<Integer, T>> {

    @Override
    public Result<Integer, T> apply(Integer integer) {
        return Result.Err(integer * integer);
    }
}
