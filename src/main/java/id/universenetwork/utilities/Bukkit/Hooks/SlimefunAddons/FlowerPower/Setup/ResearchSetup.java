package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.Setup;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import org.bukkit.inventory.ItemStack;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.FlowerPowerItems.*;

/**
 * Registers all of the research
 *
 * @author NCBPFluffyBear
 */
public final class ResearchSetup {
    public static void setup() {
        register("magic_crafting", 2711, "Magic Crafting", 5, MAGIC_BASIN, MAGICAL_WAND);
        register("experience_cauldron", 2712, "Experience Cauldron", 5, EXPERIENCE_CAULDRON);
        register("glistening_resources", 2713, "Glistening Resources", 10, MAGIC_CREAM, GLISTENING_POPPY, GLISTENING_DANDELION, GLISTENING_OXEYE_DAISY, GLISTENING_ALLIUM, RED_CRYSTAL, YELLOW_CRYSTAL, WHITE_CRYSTAL, PURPLE_CRYSTAL);
        register("experience_storage", 2714, "Experience Storage", 50, EXPERIENCE_TOME);
        register("attribute_charms", 2715, "Attribute Charms", 50, MOVEMENT_SPEED_CHARM, ATTACK_SPEED_CHARM, FLY_SPEED_CHARM, DAMAGE_CHARM, HEALTH_CHARM);
        register("recall_teleportation", 2716, "Recall Teleportation", 30, RECALL_CHARM);
        register("infinity_magic", 2717, "Infinity Magic", 30, INFINITY_APPLE, INFINITY_BANDAGE);
        register("faster_flower_growth", 2718, "Faster Flower Growth", 10, OVERGROWTH_SEED);
    }

    static void register(String key, int ID, String name, int defaultCost, ItemStack... items) {
        Research research = new Research(new org.bukkit.NamespacedKey(id.universenetwork.utilities.Bukkit.UNUtilities.plugin, key), ID, name, defaultCost);
        for (ItemStack item : items) {
            SlimefunItem sfItem = SlimefunItem.getByItem(item);
            if (sfItem != null) research.addItems(sfItem);
        }
        research.register();
    }
}