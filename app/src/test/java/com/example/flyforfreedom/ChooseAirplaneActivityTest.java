package com.example.flyforfreedom;

import android.test.AndroidTestCase;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChooseAirplaneActivityTest {

    @Test
    public void chooseAirplaneTest(){
        ChooseAirplaneActivity.GuidePageChangeListener.pageSelect(0);
        assertEquals("空中巴士A350",ChooseAirplaneActivity.getName());
        assertEquals("912",ChooseAirplaneActivity.getSpeed());
    }
    @Test
    public void chooseAirplaneTest2(){
        ChooseAirplaneActivity.GuidePageChangeListener.pageSelect(1);
        assertEquals("波音747",ChooseAirplaneActivity.getName());
        assertEquals("940",ChooseAirplaneActivity.getSpeed());
    }
    @Test
    public void chooseAirplaneTest3(){
        ChooseAirplaneActivity.GuidePageChangeListener.pageSelect(2);
        assertEquals("波音777",ChooseAirplaneActivity.getName());
        assertEquals("989",ChooseAirplaneActivity.getSpeed());
    }
    @Test
    public void chooseAirplaneTest4(){
        ChooseAirplaneActivity.GuidePageChangeListener.pageSelect(3);
        assertEquals("直升機AC311",ChooseAirplaneActivity.getName());
        assertEquals("242",ChooseAirplaneActivity.getSpeed());
    }
    @Test
    public void chooseAirplaneTest5(){
        ChooseAirplaneActivity.GuidePageChangeListener.pageSelect(4);
        assertEquals("UFO",ChooseAirplaneActivity.getName());
        assertEquals("0",ChooseAirplaneActivity.getSpeed());
    }

}
