package com.Monads;

import com.Monads.helper.ErrorMapDoubleToString;
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
        Result<Double, Integer> res = Result.Err(3.0);
        Result<Double, Integer> map = res.map(new OkMapSquareFunction());
        assertTrue(map.isError());
        assertEquals(Double.valueOf(3), map.error());
    }

    @Test
    void mapOrOnErrorTest() {
        Result<Double, Integer> res = Result.Err(3.0);
        Result<Double, Integer> map = res.mapOr(new OkMapSquareFunction(), 2);
        assertTrue(map.isOk());
        assertEquals(Integer.valueOf(2), map.ok());
    }

    @Test
    void mapErrorOnErrorTest() {
        Result<Double, Integer> res = Result.Err(3.0);
        Result<String, Integer> map =
                res
                        .mapError(new ErrorMapSquareFunction())
                        .mapError(new ErrorMapDoubleToString());
        assertTrue(map.isError());
        assertEquals("9.0", map.error());
    }
    @Test
    void mapAndMapErrorMixOnErrorTest() {
        Result<Double, Object> res = Result.Err(3.0);
        Result<String, Integer> map = res.map(o -> 2).mapError(new ErrorMapSquareFunction()).mapError(new ErrorMapDoubleToString());
        assertTrue(map.isError());
        assertEquals("9.0", map.error());
    }
}