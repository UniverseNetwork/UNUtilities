package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Gear;

import io.github.thebusybiscuit.slimefun4.core.attributes.ProtectionType;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

/**
 * armor
 *
 * @author Mooy1
 */
public final class InfinityArmor extends io.github.thebusybiscuit.slimefun4.implementation.items.armor.SlimefunArmorPiece implements io.github.thebusybiscuit.slimefun4.core.attributes.ProtectiveArmor, io.github.thebusybiscuit.slimefun4.core.attributes.Soulbound, io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable {
    static final NamespacedKey KEY = new NamespacedKey(id.universenetwork.utilities.Bukkit.UNUtilities.plugin, "infinity_armor");

    public InfinityArmor(io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, org.bukkit.potion.PotionEffect[] effects, org.bukkit.inventory.ItemStack[] recipe) {
        super(id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Groups.Groups.INFINITY_CHEAT, item, id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Blocks.InfinityWorkbench.TYPE, recipe, effects);
    }

    @NotNull
    @Override
    public ProtectionType[] getProtectionTypes() {
        return new ProtectionType[]{
                ProtectionType.BEES, ProtectionType.RADIATION, ProtectionType.FLYING_INTO_WALL
        };
    }

    @Override
    public boolean isFullSetRequired() {
        return false;
    }

    @NotNull
    @Override
    public NamespacedKey getArmorSetId() {
        return KEY;
    }
}