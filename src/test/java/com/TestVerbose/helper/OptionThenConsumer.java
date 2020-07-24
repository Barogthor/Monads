package com.TestVerbose.helper;

import java.util.function.Consumer;

public class OptionThenConsumer implements Consumer<Integer> {
    public static Integer DEFAULT = 0;
    private Integer dummyValue = DEFAULT;

    public Integer get(){
        return dummyValue;
    }

    @Override
    public void accept(Integer integer) {
        dummyValue=integer;
    }
}
