package com.lpweb.mystic_tarot.gui;

import java.util.TreeMap;

/**
 * The class RomanNumber provides a method for converting integers to roman
 * numbers as strings.
 * From: https://stackoverflow.com/a/19759564/11132458
 */
public class RomanNumber {

    /**
     * List of roman symbols.
     */
    private final static TreeMap<Integer, String> map = new TreeMap<Integer, String>();

    // Init list.
    static {
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");
    }

    /**
     * Converts a integer to a roman number.
     * @param number the number to convert.
     * @return the roman number as a string.
     */
    public final static String toRoman(int number) {
        int l =  map.floorKey(number);
        if ( number == l ) {
            return map.get(number);
        }
        return map.get(l) + toRoman(number-l);
    }

}