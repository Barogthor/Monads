package com.Monads;

import java.util.function.Consumer;
import java.util.function.Function;

class Error<E, T> implements Result<E, T> {
    protected E value;
    Error(E value){
        this.value = value;
    }

    @Override
    public E error() {
        return value;
    }

    @Override
    public boolean isOk() {
        return false;
    }

    @Override
    public boolean isError() {
        return true;
    }

    @Override
    public <U> Result<E, U> map(Function<? super T, ? extends Result<E, U>> mapper) {
        return new Error<>(this.value);
    }

    @Override
    public <W> Result<W, T> mapError(Function<? super E, ? extends Result<W, T>> mapper) {
        return mapper.apply(this.value);
    }

    @Override
    public <U, W> Result<U, W> mapOr(Function<? super T, ? extends W> mapper, Result<U, W> other) {
        return other;
    }

    @Override
    public <U, W> Result<U, W> mapOr(Function<? super T, ? extends W> mapper, W other) {
        return new Ok<>(other);
    }

    @Override
    public Result<E, T> thenOk(Consumer<T> lambda) {
        return this;
    }

    @Override
    public Result<E, T> thenError(Consumer<E> lambda) {
        lambda.accept(this.value);
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || (obj instanceof Result && ((Result)obj).isError());
    }
}
