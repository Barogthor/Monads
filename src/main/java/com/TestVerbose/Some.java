package com.TestVerbose;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

class Some<T> implements Option<T> {
    private T value;

    Some(T value){
        this.value = value;
    }

    @Override
    public T get() {
        return this.value;
    }

    @Override
    public T orElse(T other) {
        return this.value;
    }

    @Override
    public boolean isSome() {
        return true;
    }

    @Override
    public boolean isNone() {
        return false;
    }

    @Override
    public void then(Consumer<T> lambda) {
        lambda.accept(this.value);
    }

    @Override
    public Option<T> filter(Predicate<? super T> predicate) {
        return predicate.test(this.value) ? this : new None<>();
    }

    @Override
    public <U> Option<U> map(Function<? super T, ? extends U> mapper) {
        return Option.of(mapper.apply(this.value));
    }

    @Override
    public <U> Option<U> mapOr(Function<? super T, ? extends U> mapper, Option<T> other) {
        return this.map(mapper);
    }

    @Override
    public <U> Option<U> mapOr(Function<? super T, ? extends U> mapper, T other) {
        return this.map(mapper);
    }

    @Override
    public <U> Option<U> fmap(Function<? super T, Option<U>> mapper) {
        return mapper.apply(this.value);
    }

    @Override
    public <U> Option<U> fmapOr(Function<? super T, Option<U>> mapper, Option<T> other) {
        return this.fmap(mapper);
    }

    @Override
    public <U> Option<U> fmapOr(Function<? super T, Option<U>> mapper, T other) {
        return this.fmap(mapper);
    }

    @Override
    public <U> Option<U> flatten() {
        Option lastLeaf = new Some(this.value);
        while(lastLeaf.isSome() && lastLeaf.get() instanceof Option)
            lastLeaf = (Option) lastLeaf.get();
        return (Option<U>) lastLeaf;
    }

    @Override
    public  Option<T> and(Option<T> right) {
        return right;
    }

    @Override
    public  Option<T> or(Option<T> right) {
        return this;
    }

    @Override
    public Option<T> xor(Option<T> right) {
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
