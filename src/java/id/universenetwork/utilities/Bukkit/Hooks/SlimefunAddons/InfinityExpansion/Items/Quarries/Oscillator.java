package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Quarries;

import id.universenetwork.utilities.Bukkit.Libraries.InfinityLib.Common.StackUtils;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Groups.Groups.MAIN_MATERIALS;
import static id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.InfinityExpansion.Items.Materials.Materials.MACHINE_PLATE;
import static io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType.ENHANCED_CRAFTING_TABLE;
import static io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems.BLISTERING_INGOT_3;

public final class Oscillator extends SlimefunItem {
    static final Map<String, Material> OSCILLATORS = new HashMap<>();

    @Nullable
    public static Material getOscillator(@Nullable ItemStack item) {
        if (item == null) return null;
        return OSCILLATORS.get(StackUtils.getId(item));
    }

    @NotNull
    public static SlimefunItemStack create(Material material) {
        return new SlimefunItemStack("QUARRY_OSCILLATOR_" + material.name(), material, "&b" + ItemUtils.getItemName(new ItemStack(material)) + " Oscillator", "&7Place in a quarry to give it", "&7a 50% chance of mining this material");
    }

    public Oscillator(SlimefunItemStack item) {
        super(MAIN_MATERIALS, item, ENHANCED_CRAFTING_TABLE, new ItemStack[]{MACHINE_PLATE, BLISTERING_INGOT_3, MACHINE_PLATE, BLISTERING_INGOT_3, new ItemStack(item.getType()), BLISTERING_INGOT_3, MACHINE_PLATE, BLISTERING_INGOT_3, MACHINE_PLATE});
        OSCILLATORS.put(getId(), item.getType());
    }
}