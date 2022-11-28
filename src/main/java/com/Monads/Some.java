package com.Monads;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Some<T> implements Option<T> {
    protected T value;

    protected Some(T value){
        this.value = value;
    }

    @Override
    final public T get() {
        return this.value;
    }

    @Override
    final public T orElse(T other) {
        return this.value;
    }

    @Override
    final public boolean isSome() {
        return true;
    }

    @Override
    final public boolean isNone() {
        return false;
    }

    @Override
    final public void then(Consumer<T> lambda) {
        lambda.accept(this.value);
    }

    @Override
    final public Option<T> filter(Predicate<? super T> predicate) {
        return predicate.test(this.value) ? this : new None<>();
    }

    @Override
    final public <U> Option<U> map(Function<? super T, ? extends U> mapper) {
        return Option.of(mapper.apply(this.value));
    }

    @Override
    final public <U> Option<U> mapOr(Function<? super T, ? extends U> mapper, Option<U> other) {
        return this.map(mapper);
    }

    @Override
    final public <U> Option<U> mapOr(Function<? super T, ? extends U> mapper, U other) {
        return this.map(mapper);
    }

    @Override
    final public <U> Option<U> fmap(Function<? super T, Option<U>> mapper) {
        return mapper.apply(this.value);
    }

    @Override
    final public <U> Option<U> fmapOr(Function<? super T, Option<U>> mapper, Option<T> other) {
        return this.fmap(mapper);
    }

    @Override
    final public <U> Option<U> fmapOr(Function<? super T, Option<U>> mapper, T other) {
        return this.fmap(mapper);
    }

    @Override
    final public <U> Option<U> flatten() {
        if(this.value instanceof Option)
            return ((Option<?>) this.value).flatten();
        else
            return (Option<U>) new Some<>(this.value);
    }

    final @Override
    public  Option<T> and(Option<T> right) {
        return right;
    }

    @Override
    final public  Option<T> or(Option<T> right) {
        return this;
    }

    @Override
    final public Option<T> xor(Option<T> right) {
        return right.isSome() ? new None<>() : this;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Option))
            return false;
        Option right = (Option) obj;
        return this == obj || (right.isSome() && right.get().equals(value));
    }
//
//    @Override
//    public int compareTo(Option<T> o) {
//        if(o.isNone())
//            return 1;
//        if(o.get() instanceof Comparable)
//            ((Comparable) o.get()).compareTo(this.get());
//        return 0;
//    }
}
