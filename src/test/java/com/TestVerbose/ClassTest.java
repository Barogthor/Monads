package com.TestVerbose;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.fail;


public class ClassTest {

    @DisplayName("Test sum correct")
//    @Test
    void testSumTrue() {

    }

    @DisplayName("Test sum failure")
//    @Test
    void testFail(){
        fail();
    }
}
