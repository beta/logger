package net.kyouko.logger;

import org.junit.Assert;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class LogTest {

    @Test
    public void comparesLogLevels() {
        Assert.assertTrue(Log.Level.DEBUG.lowerThan(Log.Level.INFO));
        Assert.assertTrue(Log.Level.INFO.lowerThan(Log.Level.WARNING));
        Assert.assertTrue(Log.Level.WARNING.lowerThan(Log.Level.ERROR));

        Assert.assertTrue(Log.Level.ERROR.higherThan(Log.Level.WARNING));
        Assert.assertTrue(Log.Level.WARNING.higherThan(Log.Level.INFO));
        Assert.assertTrue(Log.Level.INFO.higherThan(Log.Level.DEBUG));

        Assert.assertTrue(Log.Level.DEBUG.notHigherThan(Log.Level.DEBUG));
        Assert.assertTrue(Log.Level.DEBUG.notHigherThan(Log.Level.INFO));
        Assert.assertTrue(Log.Level.INFO.notHigherThan(Log.Level.INFO));
        Assert.assertTrue(Log.Level.INFO.notHigherThan(Log.Level.WARNING));
        Assert.assertTrue(Log.Level.WARNING.notHigherThan(Log.Level.WARNING));
        Assert.assertTrue(Log.Level.WARNING.notHigherThan(Log.Level.ERROR));
        Assert.assertTrue(Log.Level.ERROR.notHigherThan(Log.Level.ERROR));

        Assert.assertTrue(Log.Level.ERROR.notLowerThan(Log.Level.ERROR));
        Assert.assertTrue(Log.Level.ERROR.notLowerThan(Log.Level.WARNING));
        Assert.assertTrue(Log.Level.WARNING.notLowerThan(Log.Level.WARNING));
        Assert.assertTrue(Log.Level.WARNING.notLowerThan(Log.Level.INFO));
        Assert.assertTrue(Log.Level.INFO.notLowerThan(Log.Level.INFO));
        Assert.assertTrue(Log.Level.INFO.notLowerThan(Log.Level.DEBUG));
        Assert.assertTrue(Log.Level.DEBUG.notLowerThan(Log.Level.DEBUG));

        Assert.assertTrue(Log.Level.DEBUG.is(Log.Level.DEBUG));
        Assert.assertTrue(Log.Level.INFO.is(Log.Level.INFO));
        Assert.assertTrue(Log.Level.WARNING.is(Log.Level.WARNING));
        Assert.assertTrue(Log.Level.ERROR.is(Log.Level.ERROR));
        Assert.assertTrue(Log.Level.DEBUG.equals(Log.Level.DEBUG));
        Assert.assertTrue(Log.Level.INFO.equals(Log.Level.INFO));
        Assert.assertTrue(Log.Level.WARNING.equals(Log.Level.WARNING));
        Assert.assertTrue(Log.Level.ERROR.equals(Log.Level.ERROR));
    }

    @Test
    public void createsLogInstances() {
        Log log = new Log(Log.Level.WARNING, "Unit Test", "Log created by unit test");
        Assert.assertEquals(log.getLevel(), Log.Level.WARNING);
        Assert.assertTrue(log.getClient().equals("Unit Test"));
        Assert.assertTrue(log.getContent().equals("Log created by unit test"));
    }

    @Test
    public void comparesTimeString() {
        Log log = new Log(Log.Level.WARNING, "Unit Test", "Log created by unit test");
        Assert.assertTrue(log.getTimeString().equals(log.getTimeString("yyyy-MM-dd HH:mm:ss")));
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Assert.assertTrue(log.getTimeString().equals(log.getTimeString(formatter)));
        Assert.assertTrue(log.getTimeString().equals(formatter.format(log.getTime())));
    }

}
