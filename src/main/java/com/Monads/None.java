package com.Monads;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class None<T> implements Option<T> {
    None(){
    }

    @Override
    final public T get() throws NullPointerException {
        throw new NullPointerException();
    }

    @Override
    final public T orElse(T other) {
        return other;
    }

    @Override
    final public void then(Consumer<T> lambda) {
    }

    @Override
    final public boolean isSome() {
        return false;
    }

    @Override
    final public boolean isNone() {
        return true;
    }

    @Override
    final public Option<T> filter(Predicate<? super T> predicate) {
        return this;
    }

    @Override
    final public <U> Option<U> map(Function<? super T, ? extends U> mapper) {
        return new None<>();
    }

    @Override
    final public <U> Option<U> mapOr(Function<? super T, ? extends U> mapper, Option<U> other) {
        return other;
    }

    @Override
    final public <U> Option<U> mapOr(Function<? super T, ? extends U> mapper, U other) {
        return Option.of(other);
    }

    @Override
    final public <U> Option<U> fmap(Function<? super T, Option<U>> mapper) {
        return new None<>();
    }

    @Override
    final public <U> Option<U> fmapOr(Function<? super T, Option<U>> mapper, Option<T> other) {
        return other.fmap(mapper);
    }

    @Override
    final public <U> Option<U> fmapOr(Function<? super T, Option<U>> mapper, T other) {
        return this.fmapOr(mapper, Option.of(other));
    }

    @Override
    final public <U> Option<U> flatten() {
        return new None<>();
    }

    @Override
    final public Option<T> and(Option<T> right) {
        return this;
    }

    @Override
    final public Option<T> or(Option<T> right) {
        return right.isSome() ? right : this;
    }

    @Override
    final public Option<T> xor(Option<T> right) {
        return right;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Option))
            return false;
        return this == obj || ((Option)obj).isNone();
    }

//    @Override
//    public int compareTo(Option<T> o) {
//        return o.isNone() ? 0 : -1;
//    }
}
