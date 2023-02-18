package com.Monads;

import java.util.function.Consumer;
import java.util.function.Function;

public class Error<E, T> implements Result<E, T> {
    protected E value;
    protected Error(E value){
        this.value = value;
    }

    @Override
    final public E error() {
        return value;
    }

    @Override
    final public boolean isOk() {
        return false;
    }

    @Override
    final public boolean isError() {
        return true;
    }

    @Override
    final public <U> Result<E, U> map(Function<? super T, ? extends Result<E, U>> mapper) {
        return new Error<>(this.value);
    }

    @Override
    final public <W> Result<W, T> mapError(Function<? super E, ? extends Result<W, T>> mapper) {
        return mapper.apply(this.value);
    }

    @Override
    public <U> Result<E, U> mapOr(Function<? super T, ? extends Result<E,U>> mapper, Result<E, U> other) {
        return other;
    }

    @Override
    public <U> Result<E, U> mapOr(Function<? super T, ? extends Result<E,U>> mapper, U other) {
        return new Ok<>(other);
    }

    @Override
    final public Result<E, T> thenOk(Consumer<T> lambda) {
        return this;
    }

    @Override
    final public Result<E, T> thenError(Consumer<E> lambda) {
        lambda.accept(this.value);
        return this;
    }

//    @Override
//    final public boolean equals(Object obj) {
//        return this == obj || (obj instanceof Result && ((Result<?, ?>)obj).isError());
//    }
}
