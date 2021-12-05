package com.strangeone101.realbiomecondition.nms;

import com.strangeone101.realbiomecondition.IBiomeReader;
import net.minecraft.core.IRegistry;
import net.minecraft.core.IRegistryWritable;
import net.minecraft.world.level.biome.BiomeBase;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import java.util.HashMap;
import java.util.Map;

public class BiomeReader_1_17 implements IBiomeReader {
    private final Map<String, Integer> biomeMap = new HashMap<>();

    @Override
    public String getBiomeAt(Location location) {
        CraftWorld world = ((CraftWorld)location.getWorld());

        if (world == null)
            return null;

        BiomeBase biome = world.getHandle().getBiome(location.getBlockX(), location.getBlockY(), location.getBlockZ());
        return String.valueOf(getCustomBiomeRegistry().getKey(biome));
    }

    @Override
    public String getBiomeCategoryAt(Location location) {
        CraftWorld world = ((CraftWorld)location.getWorld());

        if (world == null)
            return null;

        BiomeBase biome = world.getHandle().getBiome(location.getBlockX(), location.getBlockY(), location.getBlockZ());
        return biome.t().getName();
    }

    @Override
    public Map<String, Integer> getBiomeMap() {
        if (biomeMap.isEmpty()) {
            getCustomBiomeRegistry().d().forEach(entry -> {//Name, id
                String name = entry.getKey().a().toString();
                int id = getCustomBiomeRegistry().getId(entry.getValue());
                biomeMap.put(name, id);
            });
        }

        return biomeMap;
    }

    private IRegistryWritable<BiomeBase> getCustomBiomeRegistry() {
        return ((CraftServer) Bukkit.getServer()).getHandle().getServer().getCustomRegistry().b(IRegistry.aO);
    }
}
