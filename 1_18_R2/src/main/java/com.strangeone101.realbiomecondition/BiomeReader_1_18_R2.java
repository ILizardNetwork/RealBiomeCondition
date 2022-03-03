package com.strangeone101.realbiomecondition;

import org.bukkit.Location;
import org.bukkit.block.Biome;
import org.bukkit.craftbukkit.v1_18_R2.CraftWorld;

import java.util.HashMap;
import java.util.Map;

public class BiomeReader_1_18_R2 implements IBiomeReader {
    private final Map<String, Integer> biomeMap = new HashMap<>();

    @Override
    public String getBiomeAt(Location location) {
        CraftWorld world = ((CraftWorld)location.getWorld());

        if (world == null)
            return null;

        return world.getBiome(location).getKey().getKey();
    }

    @Override
    public String getBiomeCategoryAt(Location location) {
        CraftWorld world = ((CraftWorld)location.getWorld());

        if (world == null)
            return null;

        Biome biome = world.getBiome(location.getBlockX(), location.getBlockY(), location.getBlockZ());
        return biome.getKey().getKey();
    }

    @Override
    public Map<String, Integer> getBiomeMap() {
        if (biomeMap.isEmpty()) {
            NMSHandler.biomeRegistry().entrySet().forEach(entry -> {
                String name = entry.getKey().location().toString();
                int id = NMSHandler.biomeRegistry().getId(entry.getValue());
                biomeMap.put(name, id);
            });
        }
        return biomeMap;
    }
}
