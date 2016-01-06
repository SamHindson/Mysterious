package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by Blake on 1/1/2016.
 *
 * "Really wild, General"
 */
public class Serialization {
    public void serialize(SLevel l, String path){
        try ( ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File(path)))){
           out.writeObject(l);
        } catch (IOException e) {
            System.out.println("serialization haa failed");
            System.out.println(e.getMessage());
        }
    }
}
