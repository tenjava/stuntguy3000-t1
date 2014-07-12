package com.tenjava.entries.stuntguy3000.t1.util;

import org.bukkit.Location;
import org.bukkit.Material;

public class Util {
    public static Location getBlockUnderneathLocation(Location loc) {
        Location locBelow = loc.subtract(0, 1, 0);

        if (locBelow.getY() == 0) {
            return null;
        }

        if (locBelow.getBlock().getType() == Material.AIR) {
            locBelow = getBlockUnderneathLocation(locBelow);
        }

        return locBelow;
    }
}
    