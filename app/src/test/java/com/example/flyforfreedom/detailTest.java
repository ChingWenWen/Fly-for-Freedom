package com.example.flyforfreedom;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class detailTest {
    @Test
    public void totalTest(){
        assertEquals(30000,detail.total(10,10000,1));
    }
    @Test
    public void totalTest2(){
        assertEquals(0,detail.total(4,10000,1));
    }
    @Test
    public void totalTest3(){
        assertEquals(49000,detail.total(11,10000,1));
    }
    @Test
    public void totalTest4(){
        assertEquals(80000,detail.total(10,10000,0));
    }
    @Test
    public void totalTest5(){
        assertEquals(32000,detail.total(4,10000,0));
    }
    @Test
    public void totalTest6(){
        assertEquals(99000,detail.total(11,10000,0));
    }
}
