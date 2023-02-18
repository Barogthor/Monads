package com.Monads;

import com.Monads.helper.ErrorMapSquareFunction;
import com.Monads.helper.OkMapSquareFunction;
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

    @Test
    void mapOnErrorTest() {
        Result<Integer, Integer> res = Result.Err(3);
        Result<Integer, Integer> map = res.map(new OkMapSquareFunction<>());
        assertTrue(map.isError());
        assertEquals(Integer.valueOf(3), map.error());
    }

    @Test
    void mapErrorOnErrorTest() {
        Result<Integer, Integer> res = Result.Err(3);
        Result<Integer, Integer> map = res.mapError(new ErrorMapSquareFunction<>());
        assertTrue(map.isError());
        assertEquals(Integer.valueOf(9), map.error());
    }
    @Test
    void mapOrOnErrorTest() {
        Result<Integer, Integer> res = Result.Err(3);
        Result<Integer, Integer> map = res.mapOr(new ErrorMapSquareFunction<>(), 2);
        assertTrue(map.isOk());
        assertEquals(Integer.valueOf(2), map.ok());
    }
}