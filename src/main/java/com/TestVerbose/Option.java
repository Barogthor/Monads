package com.TestVerbose;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface Option<T>
//        extends Comparable<Option<T>>
{
    T get();
    T orElse(T other);
    void then(Consumer<T> lambda);
    boolean isSome();
    boolean isNone();
    Option<T> filter(Predicate<? super T> predicate);
    <U> Option<U> map(Function<? super T, ? extends U> mapper);
    <U> Option<U> mapOr(Function<? super T, ? extends U> mapper, Option<T> other);
    <U> Option<U> mapOr(Function<? super T, ? extends U> mapper, T other);
    <U> Option<U> fmap(Function<? super T, Option<U>> mapper);
    <U> Option<U> fmapOr(Function<? super T, Option<U>> mapper, Option<T> other);
    <U> Option<U> fmapOr(Function<? super T, Option<U>> mapper, T other);
    <U> Option<U> flatten();
    Option<T> and(Option<T> right);
    Option<T> or(Option<T> right);
    Option<T> xor(Option<T> right);
    static <T> Option<T> of(T value){
        if(value == null)
            return new None<>();
        else
            return new Some<>(value);
    }
}


