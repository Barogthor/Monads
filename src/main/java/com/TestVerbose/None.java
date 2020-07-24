package com.TestVerbose;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

class None<T> implements Option<T> {
    None(){
    }

    @Override
    public T get() throws NullPointerException {
        throw new NullPointerException();
    }

    @Override
    public T orElse(T other) {
        return other;
    }

    @Override
    public void then(Consumer<T> lambda) {
    }

    @Override
    public boolean isSome() {
        return false;
    }

    @Override
    public boolean isNone() {
        return true;
    }

    @Override
    public Option<T> filter(Predicate<? super T> predicate) {
        return this;
    }

    @Override
    public <U> Option<U> map(Function<? super T, ? extends U> mapper) {
        return new None<>();
    }

    @Override
    public <U> Option<U> mapOr(Function<? super T, ? extends U> mapper, Option<T> other) {
        return other.map(mapper);
    }

    @Override
    public <U> Option<U> mapOr(Function<? super T, ? extends U> mapper, T other) {
        return this.mapOr(mapper, Option.of(other));
    }

    @Override
    public <U> Option<U> fmap(Function<? super T, Option<U>> mapper) {
        return new None<>();
    }

    @Override
    public <U> Option<U> fmapOr(Function<? super T, Option<U>> mapper, Option<T> other) {
        return other.fmap(mapper);
    }

    @Override
    public <U> Option<U> fmapOr(Function<? super T, Option<U>> mapper, T other) {
        return this.fmapOr(mapper, Option.of(other));
    }

    @Override
    public <U> Option<U> flatten() {
        return new None<>();
    }

    @Override
    public  Option<T> and(Option<T> right) {
        return this;
    }

    @Override
    public  Option<T> or(Option<T> right) {
        return right.isSome() ? right : this;
    }

    @Override
    public  Option<T> xor(Option<T> right) {
        return right;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || ((Option)obj).isNone();
    }

//    @Override
//    public int compareTo(Option<T> o) {
//        return o.isNone() ? 0 : -1;
//    }
}
