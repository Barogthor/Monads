package com.Monads;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OkTest {

    @Test
    void createTest() {
        Result<Exception, Integer> ok = new Ok<>(2);
        assertEquals(2, ok.ok());
    }

    @Test
    void failErrorTest() {
        assertThrows(UnwrapException.class, () -> {
            Result<Exception, Integer> ok = new Ok<>(2);
            ok.error();
        });
    }

    @Test
    void isOkTest(){
        Result<Exception, Integer> ok = new Ok<>(2);
        assertTrue(ok.isOk());
    }

    @Test
    void isNotErrorTest(){
        Result<Exception, Integer> ok = new Ok<>(2);
        assertFalse(ok.isError());
    }
}