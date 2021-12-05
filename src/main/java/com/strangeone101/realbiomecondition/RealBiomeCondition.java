package com.strangeone101.realbiomecondition;

import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.SkillCondition;
import io.lumine.xikage.mythicmobs.skills.conditions.ILocationCondition;
import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
import java.util.HashSet;
import java.util.Set;

@MythicCondition(author = "StrangeOne101", name = "realbiome", description = "Tests if the target is within the given list of real biomes (biomes added by datapacks)")
public class RealBiomeCondition extends SkillCondition implements ILocationCondition {

    @MythicField(name = "biome", aliases = {"b"}, description = "A list of biomes to check")
    private final Set<String> biomes = new HashSet<>();

    public RealBiomeCondition(String line, MythicLineConfig mlc, String conditionVar) {
        super(line);

        String configLine = mlc.getString(new String[] { "biome", "b" }, "plains", conditionVar);

        for (String biomeString : configLine.split(",")) {
            if (!biomeString.contains(":")) {
                biomeString = "minecraft:" + biomeString;
            }

            if (RealBiomeConditionPlugin.getPlugin().getBiomeReader().getBiomeMap().containsKey(biomeString)) {
                biomes.add(biomeString);
            } else {
                RealBiomeConditionPlugin.getPlugin().getLogger().warning("Could not locate biome with name \"" + biomeString + "\" in file "
                    + mlc.getFileName());
            }
        }
    }

    @Override
    public boolean check(AbstractLocation abstractLocation) {
        String biome = RealBiomeConditionPlugin.getPlugin().getBiomeReader().getBiomeAt(abstractLocation.toPosition().toLocation());
        return biomes.contains(biome);
    }
}
