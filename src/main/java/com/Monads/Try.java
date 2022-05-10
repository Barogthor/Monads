package com.Monads;

import java.util.function.Supplier;

public class Try<R> {
    private Supplier<R> lambda;

    public Try(Supplier<R> lambda){
        this.lambda = lambda;
    }

    public Option<R> option(){
        R res = null;
        try {
            res = lambda.get();
        } finally {

        }
        return Option.of(res);
    }
}
