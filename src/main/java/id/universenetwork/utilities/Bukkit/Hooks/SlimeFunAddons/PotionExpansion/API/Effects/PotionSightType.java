package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.PotionExpansion.API.Effects;

import org.bukkit.Color;
import org.bukkit.Material;

public enum PotionSightType {
    COAL_SIGHT(Color.BLACK, Material.COAL_ORE, Material.getMaterial("DEEPSLATE_COAL_ORE")),
    IRON_SIGHT(Color.SILVER, Material.IRON_ORE, Material.getMaterial("DEEPSLATE_IRON_ORE")),
    DIAMOND_SIGHT(Color.AQUA, Material.DIAMOND_ORE, Material.getMaterial("DEEPSLATE_DIAMOND_ORE")),
    GOLD_SIGHT(Color.YELLOW, Material.GOLD_ORE, Material.getMaterial("DEEPSLATE_GOLD_ORE"), Material.getMaterial("NETHER_GOLD_ORE")),
    LAPIS_SIGHT(Color.BLUE, Material.LAPIS_ORE, Material.getMaterial("DEEPSLATE_LAPIS_ORE")),
    REDSTONE_SIGHT(Color.RED, Material.REDSTONE_ORE, Material.getMaterial("DEEPSLATE_REDSTONE_ORE")),
    EMERALD_SIGHT(Color.GREEN, Material.EMERALD_ORE, Material.getMaterial("DEEPSLATE_EMERALD_ORE")),
    QUARTZ_SIGHT(Color.WHITE, Material.NETHER_QUARTZ_ORE),
    ANCIENT_DEBRIS_SIGHT(Color.fromRGB(42, 0, 0), Material.getMaterial("ANCIENT_DEBRIS")),
    COPPER_SIGHT(Color.ORANGE, Material.getMaterial("COPPER_ORE"), Material.getMaterial("DEEPSLATE_COPPER_ORE"));

    final Material[] ores;
    final Color color;

    PotionSightType(Color color, Material... ores) {
        this.color = color;
        this.ores = ores;
    }

    public Color getColor() {
        return color;
    }

    public Material[] getOres() {
        return ores;
    }
}