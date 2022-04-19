package id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExtraTools.Implementation.Tools;

import id.universenetwork.utilities.Bukkit.Hooks.SlimefunAddons.ExtraTools.Lists.ETItems;
import io.github.thebusybiscuit.slimefun4.core.handlers.ToolUseHandler;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import static io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems.*;

public class Hammer extends io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem<ToolUseHandler> {
    public Hammer() {
        super(ETItems.extra_tools, ETItems.HAMMER, io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType.MAGIC_WORKBENCH, new ItemStack[]{new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.STICK), new ItemStack(Material.IRON_INGOT), null, new ItemStack(Material.STICK), null});
    }

    @Override
    public ToolUseHandler getItemHandler() {
        return (e, tool, fortune, drops) -> {
            if (io.github.thebusybiscuit.slimefun4.implementation.Slimefun.getPermissionsService().hasPermission(e.getPlayer(), Hammer.this)) {
                Block b = e.getBlock();
                ItemStack drop = getDrop(b);
                if (drop != null) {
                    //Can't throw NPEs now
                    b.getLocation().getWorld().dropItemNaturally(b.getLocation(), drop);
                    e.setDropItems(false);
                }
            }
        };
    }

    public ItemStack getDrop(Block b) {
        Material m = b.getType();
        switch (m) {
            case STONE:
            case GRANITE:
            case DIORITE:
            case ANDESITE:
            case COBBLESTONE: {
                return new ItemStack(Material.GRAVEL);
            }
            case GRAVEL:
            case GRASS_BLOCK:
            case DIRT:
            case COARSE_DIRT:
            case PODZOL: {
                return new ItemStack(Material.SAND);
            }
            case IRON_ORE:
            case DEEPSLATE_IRON_ORE: {
                return IRON_DUST;
            }
            case GOLD_ORE:
            case DEEPSLATE_GOLD_ORE: {
                return GOLD_DUST;
            }
            case COPPER_ORE:
            case DEEPSLATE_COPPER_ORE: {
                return COPPER_DUST;
            }
            case NETHERRACK: {
                return new ItemStack(Material.SOUL_SAND);
            }
        }
        return null;
    }
}