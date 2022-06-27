package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte byteRandomValue = 124;
        short shortRandomValue = 32711;
        int intRandomValue = 2147483111;
        long longRandomValue = 9223372036854775807L;
        float floatRandomValue = 3.4e+38f;
        double doubleRandomValue = 1.7e+308;
        boolean booleanRandomValue = false;
        char charRandomValue = 'a';

        LOG.debug("Primitive variables: " +
                "byteRandomValue: {}, " +
                "shortRandomValue : {}, " +
                "intRandomValue : {}, " +
                "longRandomValue: {}, " +
                "floatRandomValue : {}, " +
                "doubleRandomValue : {}, " +
                "booleanRandomValue: {}, " +
                "charRandomValue: {}, ",
                byteRandomValue,
                shortRandomValue,
                intRandomValue,
                longRandomValue,
                floatRandomValue,
                doubleRandomValue,
                booleanRandomValue,
                charRandomValue);
    }
}
