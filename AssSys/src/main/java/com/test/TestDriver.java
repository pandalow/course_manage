package com.test;

import com.handler.DataHandler;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestDriver {
    @Test
    public void testLoginType(){
        try{
        assertEquals(0, DataHandler.getInstance().verifyUser("zhuang",12345));}catch (Exception e){e.printStackTrace();}
    }
}
