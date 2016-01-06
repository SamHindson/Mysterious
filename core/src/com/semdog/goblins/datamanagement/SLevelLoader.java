package com.semdog.goblins.datamanagement;

import main.SLevel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

/**
 * Created by Sam on 02-Jan-16.
 *
 * Loads a level from a mggl file. Works like a charm
 */
public class SLevelLoader {

    public static SLevel load(String name) {
        SLevel level = null;

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("data/" + name + ".mggl"));
            level = (SLevel)(objectInputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Whoops, ");
            e.printStackTrace();
        }

        if(level == null) {
            System.out.println("Aw shit buddy");
        }

        return level;
    }

}
