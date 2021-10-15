package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.MobData;

import org.bukkit.Material;

import static org.bukkit.Material.*;

public enum MobDataTier {
    // ex: chicken
    PASSIVE(1, 75, IRON_CHESTPLATE),

    // ex: slime
    NEUTRAL(1, 150, IRON_CHESTPLATE),

    // ex: zombie
    HOSTILE(2, 300, DIAMOND_CHESTPLATE),

    // ex: endermen
    ADVANCED(4, 600, DIAMOND_CHESTPLATE),

    // ex: wither
    MINI_BOSS(32, 4500, NETHERITE_CHESTPLATE),

    // ex: ender dragon
    BOSS(96, 9000, NETHERITE_CHESTPLATE);
    final int xp;
    final int energy;
    final Material material;

    MobDataTier(int xp, int energy, Material material) {
        this.xp = (int) (xp * MobSimulationChamber.XP_MULTIPLIER());
        this.energy = energy;
        this.material = material;
    }
}