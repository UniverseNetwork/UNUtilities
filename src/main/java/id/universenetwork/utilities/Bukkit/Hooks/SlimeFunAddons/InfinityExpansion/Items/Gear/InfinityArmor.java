package id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.InfinityExpansion.Items.Gear;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.attributes.ProtectionType;
import io.github.thebusybiscuit.slimefun4.core.attributes.ProtectiveArmor;
import io.github.thebusybiscuit.slimefun4.core.attributes.Soulbound;
import io.github.thebusybiscuit.slimefun4.implementation.items.armor.SlimefunArmorPiece;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import javax.annotation.Nonnull;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.InfinityExpansion.Groups.Groups.INFINITY_CHEAT;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimeFunAddons.InfinityExpansion.Items.Blocks.InfinityWorkbench.TYPE;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static io.github.thebusybiscuit.slimefun4.core.attributes.ProtectionType.*;

public final class InfinityArmor extends SlimefunArmorPiece implements ProtectiveArmor, Soulbound, NotPlaceable {
    static final NamespacedKey KEY = new NamespacedKey(plugin, "infinity_armor");

    public InfinityArmor(SlimefunItemStack item, PotionEffect[] effects, ItemStack[] recipe) {
        super(INFINITY_CHEAT, item, TYPE, recipe, effects);
    }

    @Nonnull
    @Override
    public ProtectionType[] getProtectionTypes() {
        return new ProtectionType[]{BEES, RADIATION, FLYING_INTO_WALL};
    }

    @Override
    public boolean isFullSetRequired() {
        return false;
    }

    @Nonnull
    @Override
    public NamespacedKey getArmorSetId() {
        return KEY;
    }
}