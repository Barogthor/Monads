package com.Monads;

import java.util.function.Consumer;
import java.util.function.Function;

public interface Result<E, T>
//        extends Comparable<Option<T>>
{
     default E error() throws UnwrapException {
        throw new UnwrapException("Ok cannot unwrap Error value");
    }
     default T ok() throws UnwrapException{
        throw new UnwrapException("Error cannot unwrap Ok value");
    }
//    T orElse(T other);
    Result<E, T> thenOk(Consumer<T> lambda);
    Result<E, T> thenError(Consumer<E> lambda);
//        void thenError(Consumer<V> lambda);
    boolean isOk();
    boolean isError();
    <U> Result<E, U> map(Function<? super T, ? extends Result<E, U>> mapper);
    <W> Result<W, T> mapError(Function<? super E, ? extends Result<W, T>> mapper);
    <U> Result<E, U> mapOr(Function<? super T, ? extends Result<E,U>> mapper, Result<E, U> other);
    <U> Result<E, U> mapOr(Function<? super T, ? extends Result<E,U>> mapper, U other);
//    <U, W> Result<U, W> flatMap(Function<? super T, Result<U, W>> mapper);
//    <U, W> Result<U, W> flatMapError(Function<? super E, Result<U, W>> mapper);
//    <U, W> Result<U, W> flatMapOr(Function<? super E, Result<U, W>> mapper, Result<U, W> other);
//    <U, W> Result<U, W> flatMapOr(Function<? super E, Result<U, W>> mapper, W other);
//    Result<E, T> and(Result<E, T> ok);
//    Result<E, T> or(Result<E, T> ok);
//    Result<E, T> xor(Result<E, T> ok);

    static <E, T> Result<E, T> Ok(T value){
        return new Ok<>(value);
    }

    static <E, T> Result<E, T> Err(E value){
        return new Error<>(value);
    }
}


