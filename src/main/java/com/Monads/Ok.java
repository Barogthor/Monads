package com.Monads;

import java.util.function.Consumer;
import java.util.function.Function;

public class Ok<E, T> implements Result<E, T> {
    protected T value;

    protected Ok(T value){
        this.value = value;
    }

    @Override
    final public T ok() {
        return value;
    }

    @Override
    final public boolean isOk() {
        return true;
    }

    @Override
    final public boolean isError() {
        return false;
    }

    @Override
    final public Result<E, T> thenOk(Consumer<T> lambda) {
        lambda.accept(this.value);
        return this;
    }

    @Override
    final public Result<E, T> thenError(Consumer<E> lambda) {
        return this;
    }

    @Override
    final public <U> Result<E, U> map(Function<? super T, ? extends Result<E, U>> mapper) {
        return mapper.apply(this.value);
    }

    @Override
    final public <W> Result<W, T> mapError(Function<? super E, ? extends Result<W, T>> mapper) {
        return new Ok<>(this.value);
    }

    @Override
    public <U> Result<E, U> mapOr(Function<? super T, ? extends Result<E,U>> mapper, Result<E, U> other) {
        return mapper.apply(this.value);
    }

    @Override
    public <U> Result<E, U> mapOr(Function<? super T, ? extends Result<E,U>> mapper, U other) {
        return mapper.apply(this.value);
    }


    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Result))
            return false;
        Result<?, ?> res = (Result<?, ?>) obj;
        return this == obj || (res.isOk() && res.ok().equals(value));
    }

//    @Override
//    public int compareTo(Result<T> o) {
//        if(o.isNone())
//            return -1;
//
//    }
}
