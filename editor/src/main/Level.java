package main;

import java.io.Serializable;

/**
 * Created by Blake on 1/1/2016.
 */
public class Level implements Serializable {
    int[][][] level;

    public Level( Grid[] g) {
        level = new int[8][20][20];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 20; j++) {
                for (int k = 0; k < 20; k++) {
                    level[i][j][k] = g[i].getG()[j][k].getID();
                }
            }
        }
        System.out.println(toString());

    }


    @Override
    public String toString() {
        String temp ="";
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 20; j++) {
                for (int k = 0; k < 20; k++) {
                    temp+=""+Integer.toHexString(level[i][j][k])+" ";
                }
                temp+="\n";
            }
            temp+="--------------------------------------\n";
        }
        return temp;
    }
}
