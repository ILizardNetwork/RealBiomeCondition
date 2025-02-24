package com.strangeone101.realbiomecondition;

import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.SkillCondition;
import io.lumine.xikage.mythicmobs.skills.conditions.ILocationCondition;
import io.lumine.xikage.mythicmobs.util.annotations.MythicCondition;
import io.lumine.xikage.mythicmobs.util.annotations.MythicField;
import java.util.HashSet;
import java.util.Set;

@MythicCondition(author = "StrangeOne101", name = "biometype", description = "Tests if the target is within the given list of biome types")
public class BiomeTypeCondition extends SkillCondition implements ILocationCondition {

    @MythicField(name = "type", aliases = {"t"}, description = "A list of biome types to check")
    private final Set<String> biomes = new HashSet<>();


    public BiomeTypeCondition(String line, MythicLineConfig mlc, String conditionVar) {
        super(line);

        String b = mlc.getString(new String[] { "type", "t" }, "forest", conditionVar);
        for (String s : b.split(",")) {
            this.biomes.add(s.toLowerCase());
        }
    }

    @Override
    public boolean check(AbstractLocation abstractLocation) {
        return biomes.contains(RealBiomeConditionPlugin.getPlugin().getBiomeReader().getBiomeCategoryAt(abstractLocation.toPosition().toLocation()));
    }
}
