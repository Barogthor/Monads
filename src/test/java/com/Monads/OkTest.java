package com.Monads;

import com.Monads.helper.ErrorMapSquareFunction;
import com.Monads.helper.OkMapSquareFunction;
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

    @Test
    void mapOkTest() {
        Result<Exception, Integer> res = new Ok<>(2);
        Result<Exception, Integer> map = res.map(new OkMapSquareFunction<>());
        assertTrue(map.isOk());
        assertEquals(4, map.ok());
    }
    @Test
    void mapErrorOnOkTest() {
        Result<Integer, Integer> res = Result.Ok(2);
        Result<Integer, Integer> map = res.mapError(new ErrorMapSquareFunction<>());
        assertTrue(map.isOk());
        assertEquals(Integer.valueOf(2), map.ok());
    }

    @Test
    void mapOrOnErrorTest() {
        Result<Integer, Integer> res = Result.Ok(3);
        Result<Integer, Integer> map = res.mapOr(new OkMapSquareFunction<>(), 2);
        assertTrue(map.isOk());
        assertEquals(Integer.valueOf(9), map.ok());
    }

}