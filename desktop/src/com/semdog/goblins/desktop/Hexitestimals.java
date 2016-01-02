package com.semdog.goblins.desktop;

/**
 * Created by Sam on 01-Jan-16 because I'm hung over and confused about how numbers work.
 * mmmmmhh
 */
public class Hexitestimals {
    public static void main(String[] args) {
        int k = 0x900000;
        int p = (k & 0xF00000) >> 20;
        ph(p);
    }

    private static void ph(int h) {
        System.out.println(Integer.toHexString(h));
    }
}
