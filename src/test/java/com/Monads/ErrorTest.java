package com.Monads;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ErrorTest {

    @Test
    void createTest() {
        Result<Exception, Integer> result = new Error<>(new NullPointerException());
        assertEquals(NullPointerException.class, result.error().getClass());
    }

    @Test
    void failErrorTest(){
        assertThrows(UnwrapException.class, () -> {
            Result<Exception, Integer> result = new Error<>(new NullPointerException());
            result.ok();
        });
    }

    @Test
    void isNotOkTest(){
        Result<Exception, Integer> result = new Error<>(new NullPointerException());
        assertFalse(result.isOk());
    }

    @Test
    void isErrorTest(){
        Result<Exception, Integer> result = new Error<>(new NullPointerException());
        assertTrue(result.isError());
    }
}