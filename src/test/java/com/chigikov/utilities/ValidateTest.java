package com.chigikov.utilities;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by andrey on 08.01.2016.
 */
public class ValidateTest {
    @Test
    public void isValidTaskTestWithFourParameters(){
        assertTrue(Validate.isValidTask("test","12-12-2012","12-12-2012","text"));
        assertFalse(Validate.isValidTask("", "12-12-2012", "12-12-2012", "text"));
        assertFalse(Validate.isValidTask("test", "", "12-12-2012", "text"));
        assertFalse(Validate.isValidTask("test", "12-12-2012", "", "text"));
        assertFalse(Validate.isValidTask("test", "12-12-2012", "12-12-2012", ""));
    }
    @Test
    public void isValidTaskTestWithThreeParameters (){
        assertTrue(Validate.isValidTask("test","12-12-2012","text"));
        assertFalse(Validate.isValidTask("","12-12-2012","text"));
        assertFalse(Validate.isValidTask("test", "","text"));
        assertFalse(Validate.isValidTask("test", "12-12-2012", ""));
    }
}
