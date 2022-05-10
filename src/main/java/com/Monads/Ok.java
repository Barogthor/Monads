package com.Monads;

import java.util.function.Consumer;
import java.util.function.Function;

class Ok<E, T> implements Result<E, T> {
    private T value;

    Ok(T value){
        this.value = value;
    }

    @Override
    public T ok() {
        return value;
    }

    @Override
    public boolean isOk() {
        return true;
    }

    @Override
    public boolean isError() {
        return false;
    }

    @Override
    public Result<E, T> thenOk(Consumer<T> lambda) {
        lambda.accept(this.value);
        return this;
    }

    @Override
    public Result<E, T> thenError(Consumer<E> lambda) {
        return this;
    }

    @Override
    public <U> Result<E, U> map(Function<? super T, ? extends Result<E, U>> mapper) {
        return mapper.apply(this.value);
    }

    @Override
    public <W> Result<W, T> mapError(Function<? super E, ? extends Result<W, T>> mapper) {
        return new Ok<>(this.value);
    }

    @Override
    public <U, W> Result<U, W> mapOr(Function<? super T, ? extends W> mapper, Result<U, W> other) {
        return new Ok<>(mapper.apply(this.value));
    }

    @Override
    public <U, W> Result<U, W> mapOr(Function<? super T, ? extends W> mapper, W other) {
        return new Ok<>(mapper.apply(this.value));
    }

    //    @Override
//    public boolean equals(Object obj) {
//        if(!(obj instanceof Result))
//            return false;
//        Result ok = (Result) obj;
//        return this == obj || (ok.isOk() && ok.ok().equals(value));
//    }

//    @Override
//    public int compareTo(Result<T> o) {
//        if(o.isNone())
//            return -1;
//
//    }
}
