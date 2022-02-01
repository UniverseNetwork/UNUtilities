package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExoticGarden.Items;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Furnace;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import static ch.njol.skript.paperlib.PaperLib.getBlockState;
import static id.universenetwork.utilities.Bukkit.UNUtilities.plugin;
import static io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getLocalization;
import static io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils.isItemSimilar;
import static org.bukkit.Bukkit.getScheduler;
import static org.bukkit.block.BlockFace.*;

public class Kitchen extends io.github.thebusybiscuit.slimefun4.core.multiblocks.MultiBlockMachine {
    @javax.annotation.ParametersAreNonnullByDefault
    public Kitchen(io.github.thebusybiscuit.slimefun4.api.items.ItemGroup itemGroup) {
        super(itemGroup, new io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack("KITCHEN", Material.CAULDRON, "&eKitchen", "", "&a&oYou can make a bunch of different yummies here!", "&a&oThe result goes in the Furnace output slot"), new ItemStack[]{new CustomItemStack(Material.BRICK_STAIRS, "&oBrick Stairs (upside down)"), new CustomItemStack(Material.BRICK_STAIRS, "&oBrick Stairs (upside down)"), new ItemStack(Material.BRICKS), new ItemStack(Material.STONE_PRESSURE_PLATE), new ItemStack(Material.IRON_TRAPDOOR), new ItemStack(Material.BOOKSHELF), new ItemStack(Material.FURNACE), new ItemStack(Material.DISPENSER), new ItemStack(Material.CRAFTING_TABLE)}, new ItemStack[0], SELF);
    }

    @NotNull
    static Furnace locateFurnace(@NotNull Block b) {
        if (b.getRelative(EAST).getType() == Material.FURNACE)
            return (Furnace) getBlockState(b.getRelative(EAST), false).getState();
        else if (b.getRelative(WEST).getType() == Material.FURNACE)
            return (Furnace) getBlockState(b.getRelative(WEST), false).getState();
        else if (b.getRelative(NORTH).getType() == Material.FURNACE)
            return (Furnace) getBlockState(b.getRelative(NORTH), false).getState();
        else return (Furnace) getBlockState(b.getRelative(SOUTH), false).getState();
    }

    @Override
    public void onInteract(org.bukkit.entity.Player p, Block b) {
        Block dispenser = b.getRelative(DOWN);
        Furnace furnace = locateFurnace(dispenser);
        org.bukkit.inventory.FurnaceInventory furnaceInventory = furnace.getInventory();
        org.bukkit.inventory.Inventory inv = ((org.bukkit.block.Dispenser) dispenser.getState()).getInventory();
        java.util.List<ItemStack[]> inputs = RecipeType.getRecipeInputList(this);
        recipe:
        for (ItemStack[] input : inputs) {
            for (int i = 0; i < inv.getContents().length; i++) {
                if (!isItemSimilar(inv.getContents()[i], input[i], true))
                    continue recipe;
            }
            ItemStack output = RecipeType.getRecipeOutputList(this, input);
            SlimefunItem outputItem = SlimefunItem.getByItem(output);
            if (outputItem == null || outputItem.canUse(p, true)) {
                boolean canFit = furnaceInventory.getResult() == null || (furnaceInventory.getResult().getAmount() + output.getAmount() <= 64 && isItemSimilar(furnaceInventory.getResult(), output, true));
                if (!canFit) {
                    getLocalization().sendMessage(p, "machines.full-inventory", true);
                    return;
                }
                for (int i = 0; i < inv.getContents().length; i++) {
                    ItemStack item = inv.getItem(i);
                    if (item != null)
                        io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils.consumeItem(item, item.getType() == Material.MILK_BUCKET);
                }
                getScheduler().runTaskLater(plugin, () -> p.getWorld().playSound(furnace.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 1F, 1F), 55L);
                for (int i = 1; i < 7; i++)
                    getScheduler().runTaskLater(plugin, () -> p.getWorld().playSound(furnace.getLocation(), Sound.BLOCK_METAL_PLACE, 7F, 1F), i * 5L);
                if (furnaceInventory.getResult() == null) furnaceInventory.setResult(output);
                else
                    furnaceInventory.getResult().setAmount(furnaceInventory.getResult().getAmount() + output.getAmount());
            }
            return;
        }
        getLocalization().sendMessage(p, "machines.pattern-not-found", true);
    }
}