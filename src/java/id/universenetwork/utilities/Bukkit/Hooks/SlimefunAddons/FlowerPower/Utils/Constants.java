package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.Utils;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;

/**
 * Stores plugin constants
 *
 * @author NCBPFluffyBear
 */
public class Constants {
    public static final String SERVER_VERSION = org.bukkit.Bukkit.getBukkitVersion();
    public static final NamespacedKey GLOW_ENCHANT = new NamespacedKey(id.universenetwork.utilities.Bukkit.UNUtilities.plugin, "flowerpower_glow_enchant");
    public static final java.util.Set<Material> flowers = new java.util.LinkedHashSet<>(java.util.Arrays.asList(Material.POPPY, Material.DANDELION, Material.OXEYE_DAISY, Material.ALLIUM));
}