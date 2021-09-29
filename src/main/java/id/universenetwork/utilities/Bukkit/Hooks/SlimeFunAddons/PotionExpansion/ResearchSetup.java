package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.PotionExpansion;

import id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.PotionExpansion.Items.PotionItems;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import org.bukkit.NamespacedKey;

import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;

public class ResearchSetup {
    public static void setup() {
        new Research(new NamespacedKey(plugin, "potions_sight"), 31001, "Potions Sight", 28).addItems(PotionItems.COAL_SIGHT, PotionItems.IRON_SIGHT, PotionItems.DIAMOND_SIGHT, PotionItems.GOLD_SIGHT, PotionItems.LAPIS_SIGHT, PotionItems.REDSTONE_SIGHT, PotionItems.EMERALD_SIGHT, PotionItems.QUARTZ_SIGHT, PotionItems.ANCIENT_DEBRIS_SIGHT, PotionItems.COPPER_SIGHT).register();
        new Research(new NamespacedKey(plugin, "powders"), 31002, "Powders!", 6).addItems(PotionItems.COAL_POWDER, PotionItems.IRON_POWDER, PotionItems.DIAMOND_POWDER, PotionItems.GOLD_POWDER, PotionItems.LAPIS_POWDER, PotionItems.REDSTONE_POWDER, PotionItems.EMERALD_POWDER, PotionItems.QUARTZ_POWDER, PotionItems.ANCIENT_DEBRIS_POWDER, PotionItems.COPPER_POWDER).register();
        new Research(new NamespacedKey(plugin, "alchemic_station"), 31003, "Alchemic Station", 15).addItems(PotionItems.ALCHEMIC_STATION).register();
    }
}