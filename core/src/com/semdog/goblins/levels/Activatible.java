package com.semdog.goblins.levels;

import com.semdog.goblins.player.Player;

/**
 * Created by Sam on 31-Dec-15.
 *
 * A nice interface for blocks that do something.
 */

public interface Activatible {
    void activate(Player player);
    int getID();
}
