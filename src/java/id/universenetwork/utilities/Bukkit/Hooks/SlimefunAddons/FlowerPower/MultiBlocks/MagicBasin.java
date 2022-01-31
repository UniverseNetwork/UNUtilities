package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.MultiBlocks;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

/**
 * This is a placeholder MultiBlockMachine purely for
 * registering recipes
 * This machine can not be built due to the item frames
 * All interaction is handled by the {@link ExperienceCauldron}
 *
 * @author NCBPFluffyBear
 */
public class MagicBasin extends io.github.thebusybiscuit.slimefun4.core.multiblocks.MultiBlockMachine {
    public static RecipeType BASIN_RECIPE;

    public MagicBasin(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup, io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack item, org.bukkit.inventory.ItemStack[] recipe, org.bukkit.block.BlockFace trigger) {
        super(itemGroup, item, recipe, trigger);
    }

    @Override
    public void onInteract(org.bukkit.entity.Player p, org.bukkit.block.Block b) {
        id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.Utils.Utils.send(p, "&cThis shouldn't be able to happen.");
    }

    static {
        BASIN_RECIPE = new RecipeType(new org.bukkit.NamespacedKey(id.universenetwork.utilities.Bukkit.UNUtilities.plugin, "magic_basin"), id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.FlowerPower.FlowerPowerItems.MAGIC_BASIN, "&7Craft it with a Magic Basin!");
    }
}