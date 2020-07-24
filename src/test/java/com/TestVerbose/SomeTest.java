package com.TestVerbose;

import com.TestVerbose.helper.OptionFlatMapSquareFunction;
import com.TestVerbose.helper.OptionMapSquareFunction;
import com.TestVerbose.helper.OptionThenConsumer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SomeTest {

    @Test
    public void testCreate(){
        Option<Integer> option = Option.of(5);
        assertTrue(option instanceof Some);
        assertEquals(5, option.get());
    }

    @Test
    public void testValueOr(){
        Option<Integer> option = Option.of(5);
        assertEquals(5, option.orElse(9));
    }

    @Test
    public void testThen(){
        String hello = "Hello world";
        Option<Integer> option = Option.of(5);
        OptionThenConsumer consumer = new OptionThenConsumer();
        option.then(consumer);
        option.then(value -> System.out.println(hello+" "+value));
        assertEquals(5, consumer.get());
    }

    @Test
    public void testFilterFalse(){
        Option<Integer> option = Option.of(5);
        Option<Integer> filterOption = option.filter(value -> value == 6);
        assertTrue(filterOption instanceof None);
    }

    @Test
    public void testFilterTrue(){
        Option<Integer> option = Option.of(5);
        Option<Integer> filterOption = option.filter(value -> value == 5);
        assertTrue(filterOption instanceof Some);
        assertEquals(5, filterOption.get());
    }

    @Test
    public void testMap(){
        Option<Integer> option = Option.of(5);
        Option<Integer> mappedOption = option.map(new OptionMapSquareFunction());
        assertTrue(mappedOption instanceof Some);
        assertEquals(25, mappedOption.get());
    }

    @Test
    public void testMap2(){
        Option<Integer> option = Option.of(5)
                .map(num -> num*num)
                .map(Object::toString)
                .map(num -> num + "10")
                .map(Integer::parseInt);
        assertEquals(2510, option.get());
    }

    @Test
    public void testFlatMap(){
        Option<Integer> option = Option.of(5);
        Option<Integer> mappedOption = option.fmap(new OptionFlatMapSquareFunction());
        assertTrue(mappedOption instanceof Some);
        assertEquals(25, mappedOption.get());
    }

    @Test
    public void testFlatten_0(){
        Option<Integer> option = Option.of(5);
        Option<Integer> flattenedOption = option.flatten();
        assertTrue(flattenedOption instanceof Some);
        assertEquals(5, flattenedOption.get());
    }

    @Test
    public void testFlatten_1(){
        Option<Option<Integer>> option = Option.of(Option.of(5));
        Option<Integer> flattenedOption = option.flatten();
        assertTrue(flattenedOption instanceof Some);
        assertEquals(5, flattenedOption.get());
    }

    @Test
    public void testFlatten_withNone(){
        Option<Option<Integer>> option = Option.of(Option.of(null));
        Option<Integer> flattenedOption = option.flatten();
        assertTrue(flattenedOption instanceof None);
    }

    @Test
    public void testFlatten_2(){
        Option<Option<Option<Integer>>> option = Option.of(Option.of(Option.of(5)));
        Option<Integer> flattenedOption = option.flatten();
        assertTrue(flattenedOption instanceof Some);
        assertEquals(5, flattenedOption.get());
    }

    @Test
    public void testAnd(){
        Option<Integer> left = Option.of(5);
        Option<Integer> right = Option.of(6);
        Option<Integer> andOption = left.and(right);
        assertEquals(right, andOption);
    }

    @Test
    public void testOr(){
        Option<Integer> left = Option.of(5);
        Option<Integer> right = Option.of(6);
        Option<Integer> orOption = left.or(right);
        assertEquals(left, orOption);
    }

    @Test
    public void testXor(){
        Option<Integer> left = Option.of(5);
        Option<Integer> right = Option.of(6);
        Option<Integer> xorOption = left.xor(right);
        assertEquals(new None<>(), xorOption);
    }

    @Test
    public void testXor_left(){
        Option<Integer> left = Option.of(5);
        Option<Integer> right = Option.of(null);
        Option<Integer> xorOption = left.xor(right);
        assertEquals(left, xorOption);
    }
}