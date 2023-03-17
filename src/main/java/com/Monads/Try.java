package com.Monads;

import java.util.function.Supplier;

public class Try<R> {
    private final Supplier<R> lambda;

    public Try(Supplier<R> lambda){
        this.lambda = lambda;
    }

    public Option<R> option(){
        R res = lambda.get();
        return Option.of(res);
    }

    public Result<Exception, R> result() {
        try {
            R res = this.lambda.get();
            return new Ok<>(res);
        }
        catch (Exception e) {
            return new Error<>(e);
        }
    }
}
