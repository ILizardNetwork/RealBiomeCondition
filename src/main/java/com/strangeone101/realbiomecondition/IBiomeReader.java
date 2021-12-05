package com.strangeone101.realbiomecondition;

import org.bukkit.Location;
import java.util.Map;

public interface IBiomeReader {

    String getBiomeAt(Location location);

    String getBiomeCategoryAt(Location location);

    /**
     * Get a map of all biomes in the game. The key is the biome name, e.g. minecraft:ocean
     * The value is the biome id
     * @return Map&lt;String, Integer&gt; - Map containing the name of the biome as <code>key</code> and the
     * Integer for the Biome ID as <code>value</code>
     */
    Map<String, Integer> getBiomeMap();
}
