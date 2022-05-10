package com.Monads;

import com.Monads.helper.OptionFlatMapSquareFunction;
import com.Monads.helper.OptionMapSquareFunction;
import com.Monads.helper.OptionThenConsumer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoneTest {

    @Test
    void testCreate(){
        Option<Integer> option = Option.of(null);
        assertTrue(option instanceof None);
    }

    @Test
    void testNoneGet(){
        assertThrows(NullPointerException.class, () -> {
            Option<Integer> option = Option.of(null);
            option.get();
        });
    }

    @Test
    void testValueOr(){
        Option<Integer> option = Option.of(null);
        assertEquals(9, option.orElse(9));
    }

    @Test
    void testThen(){
        Option<Integer> option = Option.of(null);
        OptionThenConsumer consumer = new OptionThenConsumer();
        option.then(consumer);
        assertEquals(OptionThenConsumer.DEFAULT, consumer.get());
    }

    @Test
    void testFilter(){
        Option<Integer> option = Option.of(null);
        Option<Integer> filterOption = option.filter(value -> value == 6);
        assertTrue(filterOption instanceof None);
    }

    @Test
    void testMap(){
        Option<Integer> option = Option.of(null);
        Option<Integer> mappedOption = option.map(new OptionMapSquareFunction());
        assertTrue(mappedOption instanceof None);
    }

    @Test
    void testMapOr(){
        Option<Integer> option = Option.of(null);
        Option<Integer> mappedOption = option.mapOr(new OptionMapSquareFunction(), Option.of(5));
        assertTrue(mappedOption instanceof Some);
        assertEquals(5, mappedOption.get());
    }

    @Test
    void testMapOr_defaultNull(){
//        assertThrows(NullPointerException.class, () -> {
            Option<Integer> option = Option.of(null);
            Option<Integer> mappedOption = option.mapOr(new OptionMapSquareFunction(), Option.of(null));
            assertTrue(mappedOption instanceof None);
//        });
    }

    @Test
    void testFlatMap(){
        Option<Integer> option = Option.of(null);
        Option<Integer> mappedOption = option.fmap(new OptionFlatMapSquareFunction());
        assertTrue(mappedOption instanceof None);
    }

    @Test
    void testFlatMap_defaultNull(){
//        assertThrows(NullPointerException.class, () -> {
            Option<Integer> option = Option.of(null);
            Option<Integer> mappedOption = option.fmapOr(new OptionFlatMapSquareFunction(), Option.of(null));
            assertTrue(mappedOption instanceof None);
//        });
    }

    @Test
    void testFlatten(){
        Option<Integer> option = Option.of(null);
        assertTrue(option.flatten() instanceof None);
    }


    @Test
    void testAnd(){
        Option<Integer> left = Option.of(null);
        Option<Integer> right = Option.of(6);
        Option<Integer> andOption = left.and(right);
        assertEquals(left, andOption);
    }

    @Test
    void testOr(){
        Option<Integer> left = Option.of(null);
        Option<Integer> right = Option.of(6);
        Option<Integer> orOption = left.or(right);
        assertEquals(right, orOption);
    }

    @Test
    void testXor(){
        Option<Integer> left = Option.of(null);
        Option<Integer> right = Option.of(6);
        Option<Integer> xorOption = left.xor(right);
        assertEquals(right, xorOption);
    }

}