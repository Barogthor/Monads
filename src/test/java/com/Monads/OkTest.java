package com.Monads;

import com.Monads.helper.ErrorMapDoubleToString;
import com.Monads.helper.ErrorMapSquareFunction;
import com.Monads.helper.OkMapIntegerToString;
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
        Result<Exception, String> map =
                res
                .map(new OkMapSquareFunction())
                .map(new OkMapIntegerToString());
        assertTrue(map.isOk());
        assertEquals("4", map.ok());
    }
    @Test
    void mapErrorOnOkTest() {
        Result<Double, Integer> res = Result.Ok(2);
        Result<String, Integer> map =
                res
                .mapError(new ErrorMapSquareFunction())
                .mapError(new ErrorMapDoubleToString());
        assertTrue(map.isOk());
        assertEquals(Integer.valueOf(2), map.ok());
    }

    @Test
    void mapOrOnOkTest() {
        Result<Double, Integer> res = Result.Ok(2);
        Result<Double, Integer> map = res.mapOr(new OkMapSquareFunction(), 2);
        assertTrue(map.isOk());
        assertEquals(Integer.valueOf(4), map.ok());
    }

    @Test
    void mapAndMapErrorMixOnOkTest() {
        Result<Object, Integer> res = Result.Ok(2);
        Result<Double, String> map = res.map(new OkMapIntegerToString()).mapError(o -> 3.0);
        assertTrue(map.isOk());
        assertEquals("2", map.ok());

    }


}