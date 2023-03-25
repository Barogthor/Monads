package com.Monads;

import java.util.function.Consumer;
import java.util.function.Function;
/**
 * <p>Wrapper of a value with 2 variants, one is for success, the other for error.
 * Used to return and propagating errors that are expected and can be recoverable.
 * </p>
 * It is associated with utility methods that takes a function and apply it to the wrapped value
 * or can even change one of the wrapped type parameter.
 *
 * @param <T> the type of the value for success case
 * @param <E> the type of the value for error case
 */
public interface Result<E, T>
//        extends Comparable<Option<T>>
{
    /**
     *  Getter of the wrapped value for Error result
     *
     * @return Returns the wrapped error value
     * @throws UnwrapException if called by {@code Result.Ok}
     */
     default E error() throws UnwrapException {
        throw new UnwrapException("Ok cannot unwrap Error value");
    }
    /**
     *  Getter of the wrapped value for successful result
     *
     * @return Returns the wrapped ok value
     * @throws UnwrapException if called by {@code Result.Error}
     */
     default T ok() throws UnwrapException{
        throw new UnwrapException("Error cannot unwrap Ok value");
    }
//    T orElse(T other);
    Result<E, T> thenOk(Consumer<T> lambda);
    Result<E, T> thenError(Consumer<E> lambda);
    boolean isOk();
    boolean isError();
    <U> Result<E, U> map(Function<? super T, ? extends U> mapper);
    <W> Result<W, T> mapError(Function<? super E, ? extends W> mapper);
    <U> Result<E, U> mapOr(Function<? super T, ? extends U> mapper, U other);
    <U> Result<E, U> mapOr(Function<? super T, ? extends U> mapper, Result<E, U> other);
    <U> Result<E, U> fmap(Function<? super T, ? extends Result<E, U>> mapper);
    <W> Result<W, T> fmapError(Function<? super E, ? extends Result<W, T>> mapper);
    <U> Result<E, U> fmapOr(Function<? super T, ? extends Result<E,U>> mapper, Result<E, U> other);
    <U> Result<E, U> fmapOr(Function<? super T, ? extends Result<E,U>> mapper, U other);
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


