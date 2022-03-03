package com.strangeone101.realbiomecondition;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.world.level.biome.Biome;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_18_R2.CraftServer;
import org.bukkit.craftbukkit.v1_18_R2.CraftWorld;

public class NMSHandler {
    public static BlockPos locToBlockPos(Location loc) {
        return new BlockPos(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
    }

    public static Biome getBiome(Location loc) {
        CraftWorld cw = ((CraftWorld)loc.getWorld());
        if (cw == null)
            return null;
        return ((CraftWorld)loc.getWorld()).getHandle().getBiome(NMSHandler.locToBlockPos(loc)).value();
    }

    public static Registry<Biome> biomeRegistry() {
        return ((CraftServer) Bukkit.getServer()).getHandle().getServer().registryHolder.registryOrThrow(Registry.BIOME_REGISTRY);
    }
}
